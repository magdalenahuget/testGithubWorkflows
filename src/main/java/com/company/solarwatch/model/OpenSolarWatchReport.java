package com.company.solarwatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenSolarWatchReport(
        OpenSolarData results) {
}
