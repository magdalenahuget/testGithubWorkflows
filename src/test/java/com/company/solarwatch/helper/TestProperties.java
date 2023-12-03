package com.company.solarwatch.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestProperties {

    @Value("${api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}