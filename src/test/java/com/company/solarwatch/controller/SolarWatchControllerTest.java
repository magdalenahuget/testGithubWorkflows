package com.company.solarwatch.controller;

import com.company.solarwatch.model.SolarWatchReport;
import com.company.solarwatch.service.SolarWatchService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SolarWatchControllerTest {
    private MockMvc mockMvc;

    @Mock
    SolarWatchService solarWatchService;

    @InjectMocks
    private SolarWatchController solarWatchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(solarWatchController).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSunriseTime() throws Exception {
        // GIVEN
        //        {
//            "city": "paris",
//                "date": "2023-05-25",
//                "sunrise": "03:55:38",
//                "sunset": "19:39:38"
//        }
        String cityName = "paris";
        LocalDate date = LocalDate.of(2023, 5, 25);

        SolarWatchReport solarWatchReport = new SolarWatchReport("paris",
                LocalDate.of(2023, 5, 25),
                LocalTime.of(3, 55, 38),
                LocalTime.of(19, 39, 38));
        when(solarWatchService.getSolarWatchReport(cityName, date)).thenReturn(solarWatchReport);

        mockMvc.perform(get("/solar-watch?city={cityName}&date={date}", cityName, date))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.city").value(cityName));

    }
}