package com.example.springt5app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:demo.properties")
public class AppPropertiesConfig {

    @Value("${webDriver:chrome}")
    private String webDriver;

    @Value("${pathToChromeDriver}")
    private String pathToChromeDriver;


    public String getPathToChromeDriver() {
        return pathToChromeDriver;
    }

    public String getWebDriver() {
        return webDriver;
    }
}
