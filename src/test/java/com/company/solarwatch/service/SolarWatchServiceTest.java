//package com.company.solarwatch.service;
//
//import com.company.solarwatch.configuration.ConfigProperties;
//import com.company.solarwatch.model.OpenGeoReport;
//import com.company.solarwatch.model.OpenSolarWatchReport;
//import com.company.solarwatch.model.OpenSolarData;
//import com.company.solarwatch.model.SolarWatchReport;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class SolarWatchServiceTest {
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private ConfigProperties configProperties;
//
//    @InjectMocks
//    private SolarWatchService solarWatchService;
//
//    @Test
//    void getSolarWatchReport_validCityName_returnsNotNull() {
//        // GIVEN
//        String cityName = "Paris";
//        LocalDate date = LocalDate.now();
//        OpenGeoReport mockGeoResponse = new OpenGeoReport(cityName, null, 48.8588897, 2.3200410217200766, "France", "France");
//        when(restTemplate.getForObject(any(String.class), any())).thenReturn(new OpenGeoReport[]{mockGeoResponse});
//
//        OpenSolarWatchReport mockSolarResponse = new OpenSolarWatchReport(new OpenSolarData("17:30:24", "05:49:52"));
//        when(restTemplate.getForObject(any(String.class), Mockito.eq(OpenSolarWatchReport.class)))
//                .thenReturn(mockSolarResponse);
//
//        // WHEN
//        SolarWatchReport solarWatchReport = solarWatchService.getSolarWatchReport(cityName, date);
//
//        // THEN
////        assertNotNull(solarWatchReport);
//        assertEquals(solarWatchReport.city(), cityName);
//        // Add more assertions based on the expected behavior of your service method
//    }
//
//}