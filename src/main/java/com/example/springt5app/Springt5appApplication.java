package com.example.springt5app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("demo.properties")
public class Springt5appApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springt5appApplication.class, args);
    }

}
