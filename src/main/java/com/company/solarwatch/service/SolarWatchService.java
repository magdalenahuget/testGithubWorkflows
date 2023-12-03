package com.company.solarwatch.service;

import com.company.solarwatch.configuration.ConfigProperties;
import com.company.solarwatch.exception.CityNotFoundException;
import com.company.solarwatch.exception.NoDataFoundInSunriseSunsetApi;
import com.company.solarwatch.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class SolarWatchService {
    private final RestTemplate restTemplate;
    private final ConfigProperties configProperties;
    private static final Logger logger = LoggerFactory.getLogger(SolarWatchService.class);

    @Autowired
    public SolarWatchService(RestTemplate restTemplate, ConfigProperties configProperties) {
        this.restTemplate = restTemplate;
        this.configProperties = configProperties;
    }
    public SolarWatchReport getSolarWatchReport(String city, LocalDate date) {
        String API_KEY = configProperties.getConfigValue("api.key");

        if (API_KEY == null) {
            API_KEY = System.getenv("API_KEY");
        }
        System.out.println("API KEY " + API_KEY);
        String urlGeo = String.format("https://api.openweathermap.org/geo/1.0/direct?q=%s&appid=%s", city, API_KEY);

        OpenGeoReport[] responseGeo = restTemplate.getForObject(urlGeo, OpenGeoReport[].class);
        logger.info("Response from Open Geo API: {}", responseGeo);

        if (responseGeo != null && responseGeo.length > 0) {
            OpenGeoReport openGeoReport = responseGeo[0];

            Double lat = openGeoReport.lat();
            Double lon = openGeoReport.lon();

            String urlSunriseSunset = String.format("https://api.sunrise-sunset.org/json?lat=%s&lng=%s&date=%s", lat, lon, date);
            logger.info("Requesting data from Open Solar API: {}", urlSunriseSunset);
            OpenSolarWatchReport responseSolar = restTemplate.getForObject(urlSunriseSunset, OpenSolarWatchReport.class);
            logger.info("Response from Open Solar API: {}", responseSolar);

            if (responseSolar != null && responseSolar.results() != null) {

                logger.info("Raw response from Open Solar API: {}", responseSolar);

                String sunriseStr = responseSolar.results().sunrise();
                String sunsetStr = responseSolar.results().sunset();
                logger.info("Raw response from Open Solar API: {}", responseSolar);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a", Locale.ENGLISH);
                LocalTime sunrise = LocalTime.parse(sunriseStr, formatter);
                LocalTime sunset = LocalTime.parse(sunsetStr, formatter);

                return new SolarWatchReport(city, date, sunrise, sunset);
            } else {
                throw new NoDataFoundInSunriseSunsetApi();
            }
        } else {
            throw new CityNotFoundException(city);
        }
    }
}
