package com.kat.farmshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DayMultiplierProps.class)
public class FarmshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarmshopApplication.class, args);
    }

}
