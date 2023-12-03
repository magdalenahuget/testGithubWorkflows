package com.company.solarwatch.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record SolarWatchReport(
        String city,
        LocalDate date,
        LocalTime sunrise,
        LocalTime sunset) {
}
