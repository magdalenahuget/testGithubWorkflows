package com.company.solarwatch.service;

import com.company.solarwatch.configuration.ConfigProperties;
import com.company.solarwatch.exception.CityNotFoundException;
import com.company.solarwatch.model.OpenGeoReport;
import com.company.solarwatch.model.OpenSolarWatchReport;
import com.company.solarwatch.model.OpenSolarData;
import com.company.solarwatch.model.SolarWatchReport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class SolarWatchServiceTest2 {

    @Value("${api.key}")
    private String configApiKey;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ConfigProperties configProperties;

    @InjectMocks
    private SolarWatchService solarWatchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetSolarWatchReportSuccess() throws JsonProcessingException {
        String cityName = "paris";
        LocalDate date = LocalDate.of(2023, 05, 25);
        String geoApiKey = configApiKey;
        System.out.println(geoApiKey);
        String geoApiUrl = String.format("https://api.openweathermap.org/geo/1.0/direct?q=%s&appid=%s", cityName, geoApiKey);
        Double lat = 48.8588897;
        Double lon = 2.3200410217200766;
        String country = "FR";
        String state = "Ile-de-France";
        String json = "{\n" +
                "            \"te\": \"పారిస్\",\n" +
                "            \"af\": \"Parys\",\n" +
                "            \"fr\": \"Paris\",\n" +
                "            \"am\": \"ፓሪስ\",\n" +
                "            \"hy\": \"Փարիզ\",\n" +
                "            \"ml\": \"പാരിസ്\",\n" +
                "            \"yo\": \"Parisi\",\n" +
                "            \"la\": \"Lutetia\",\n" +
                "            \"gn\": \"Parĩ\",\n" +
                "            \"oc\": \"París\",\n" +
                "            \"it\": \"Parigi\",\n" +
                "            \"ht\": \"Pari\",\n" +
                "            \"mn\": \"Парис\",\n" +
                "            \"co\": \"Parighji\",\n" +
                "            \"ba\": \"Париж\",\n" +
                "            \"ca\": \"París\",\n" +
                "            \"kv\": \"Париж\",\n" +
                "            \"zh\": \"巴黎\",\n" +
                "            \"ar\": \"باريس\",\n" +
                "            \"gl\": \"París\",\n" +
                "            \"km\": \"ប៉ារីស\",\n" +
                "            \"sh\": \"Pariz\",\n" +
                "            \"ln\": \"Pari\",\n" +
                "            \"yi\": \"פאריז\",\n" +
                "            \"nl\": \"Parijs\",\n" +
                "            \"sr\": \"Париз\",\n" +
                "            \"mi\": \"Parī\",\n" +
                "            \"et\": \"Pariis\",\n" +
                "            \"cs\": \"Paříž\",\n" +
                "            \"lb\": \"Paräis\",\n" +
                "            \"sv\": \"Paris\",\n" +
                "            \"fa\": \"پاریس\",\n" +
                "            \"sq\": \"Parisi\",\n" +
                "            \"sc\": \"Parigi\",\n" +
                "            \"ky\": \"Париж\",\n" +
                "            \"so\": \"Baariis\",\n" +
                "            \"de\": \"Paris\",\n" +
                "            \"hi\": \"पैरिस\",\n" +
                "            \"wo\": \"Pari\",\n" +
                "            \"hu\": \"Párizs\",\n" +
                "            \"tt\": \"Париж\",\n" +
                "            \"mk\": \"Париз\",\n" +
                "            \"ps\": \"پاريس\",\n" +
                "            \"or\": \"ପ୍ୟାରିସ\",\n" +
                "            \"tl\": \"Paris\",\n" +
                "            \"is\": \"París\",\n" +
                "            \"mr\": \"पॅरिस\",\n" +
                "            \"he\": \"פריז\",\n" +
                "            \"ku\": \"Parîs\",\n" +
                "            \"vi\": \"Paris\",\n" +
                "            \"sk\": \"Paríž\",\n" +
                "            \"no\": \"Paris\",\n" +
                "            \"ka\": \"პარიზი\",\n" +
                "            \"lt\": \"Paryžius\",\n" +
                "            \"ru\": \"Париж\",\n" +
                "            \"li\": \"Paries\",\n" +
                "            \"ne\": \"पेरिस\",\n" +
                "            \"my\": \"ပါရီမြို့\",\n" +
                "            \"ko\": \"파리\",\n" +
                "            \"br\": \"Pariz\",\n" +
                "            \"pl\": \"Paryż\",\n" +
                "            \"bo\": \"ཕ་རི།\",\n" +
                "            \"uk\": \"Париж\",\n" +
                "            \"uz\": \"Parij\",\n" +
                "            \"el\": \"Παρίσι\",\n" +
                "            \"gu\": \"પૅરિસ\",\n" +
                "            \"pa\": \"ਪੈਰਿਸ\",\n" +
                "            \"an\": \"París\",\n" +
                "            \"ta\": \"பாரிஸ்\",\n" +
                "            \"ja\": \"パリ\",\n" +
                "            \"bn\": \"প্যারিস\",\n" +
                "            \"be\": \"Парыж\",\n" +
                "            \"ga\": \"Páras\",\n" +
                "            \"tg\": \"Париж\",\n" +
                "            \"eo\": \"Parizo\",\n" +
                "            \"za\": \"Bahliz\",\n" +
                "            \"zu\": \"IParisi\",\n" +
                "            \"es\": \"París\",\n" +
                "            \"cv\": \"Парис\",\n" +
                "            \"th\": \"ปารีส\",\n" +
                "            \"ha\": \"Pariis\",\n" +
                "            \"os\": \"Париж\",\n" +
                "            \"sl\": \"Pariz\",\n" +
                "            \"kn\": \"ಪ್ಯಾರಿಸ್\",\n" +
                "            \"cu\": \"Парижь\",\n" +
                "            \"fi\": \"Pariisi\",\n" +
                "            \"bg\": \"Париж\",\n" +
                "            \"gv\": \"Paarys\",\n" +
                "            \"bs\": \"Pariz\",\n" +
                "            \"ug\": \"پارىژ\",\n" +
                "            \"ur\": \"پیرس\",\n" +
                "            \"lv\": \"Parīze\",\n" +
                "            \"eu\": \"Paris\",\n" +
                "            \"fy\": \"Parys\",\n" +
                "            \"hr\": \"Pariz\",\n" +
                "            \"wa\": \"Paris\",\n" +
                "            \"kk\": \"Париж\",\n" +
                "            \"tk\": \"Pariž\"\n" +
                "        }";
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> localNamesMap = objectMapper.readValue(json, HashMap.class);
        OpenGeoReport[] openGeoReports = {new OpenGeoReport(cityName, localNamesMap, lat, lon, country, state)};

        String solarApiUrl = String.format("https://api.sunrise-sunset.org/json?lat=%s&lng=%s&date=%s", lat, lon, date);
        OpenSolarWatchReport openSolarWatchReport = new OpenSolarWatchReport(new OpenSolarData("3:55:38 AM", "7:39:38 PM"));

        System.out.println("qweqw " + geoApiKey);
        when(configProperties.getConfigValue("api.key")).thenReturn(geoApiKey);
        String testApiKeyGet = configProperties.getConfigValue("api.key");
        System.out.println("testApiKeyGet = " + testApiKeyGet);
        when(restTemplate.getForObject(geoApiUrl, OpenGeoReport[].class)).thenReturn(openGeoReports);
        when(restTemplate.getForObject(solarApiUrl, OpenSolarWatchReport.class)).thenReturn(openSolarWatchReport);

        SolarWatchReport report = solarWatchService.getSolarWatchReport(cityName, date);

        assertNotNull(report);
        assertEquals(cityName, report.city());
        assertEquals(date, report.date());
        assertEquals(LocalTime.of(3, 55, 38), report.sunrise());
        assertEquals(LocalTime.of(19, 39, 38), report.sunset());
    }

    @Test
    void testGetSolarWatchReportCityNotFoundException() {
        String cityName = "NonExistentCity";
        LocalDate date = LocalDate.of(2023, 10, 07);
        String apiKey = configApiKey;
        String geoApiUrl = String.format("https://api.openweathermap.org/geo/1.0/direct?q=%s&appid=%s", cityName, apiKey);
        System.out.println(configApiKey);

        when(configProperties.getConfigValue("api.key")).thenReturn(apiKey);
        when(restTemplate.getForObject(geoApiUrl, OpenGeoReport[].class)).thenReturn(null);

        assertThrows(CityNotFoundException.class, () -> {
            solarWatchService.getSolarWatchReport(cityName, date);
        });
    }

    @Test
    void testGetSolarWatchReportNoDataFoundInGeoApi() {
        String cityName = "CityName";
        LocalDate date = LocalDate.of(2023, 10, 07);
        String apiKey = "your_api_key";
        String geoApiUrl = String.format("https://api.openweathermap.org/geo/1.0/direct?q=%s&appid=%s", cityName, apiKey);

        OpenGeoReport[] openGeoReports = {};
        when(configProperties.getConfigValue("api.key")).thenReturn(apiKey);
        when(restTemplate.getForObject(geoApiUrl, OpenGeoReport[].class)).thenReturn(openGeoReports);

        assertThrows(CityNotFoundException.class, () -> {
            solarWatchService.getSolarWatchReport(cityName, date);
        });
    }
}
