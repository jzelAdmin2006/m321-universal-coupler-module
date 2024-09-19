package com.jzel.m321universalcouplermodule.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
public class StationConfig {

    @Value("${station.number}")
    private String number;

    public static int STATION_NUMBER;

    @PostConstruct
    public void init() {
        STATION_NUMBER = Integer.parseInt(number);
    }
}