package com.company.solarwatch.controller;

import com.company.solarwatch.exception.CityNotFoundException;
import com.company.solarwatch.exception.IncorrectFormatDateException;
import com.company.solarwatch.model.SolarWatchReport;
import com.company.solarwatch.service.SolarWatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class SolarWatchController {

    private final SolarWatchService solarWatchService;

    public SolarWatchController(SolarWatchService solarWatchService) {
        this.solarWatchService = solarWatchService;
    }

    @GetMapping("/solar-watch")
    public ResponseEntity<?> getSunriseTime(
            @RequestParam("city") String city,
            @RequestParam("date") LocalDate date) {
        try {
            SolarWatchReport solarWatchReport = solarWatchService.getSolarWatchReport(city, date);
            return ResponseEntity.ok(solarWatchReport);
        } catch (CityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } catch (IncorrectFormatDateException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
