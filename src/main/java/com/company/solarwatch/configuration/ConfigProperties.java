package com.company.solarwatch.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:application.properties")
public class ConfigProperties {

    @Value("${api.key}")
    private String apiKey;

    public String getConfigValue(String apiValue) {
        return apiKey;
    }
}
