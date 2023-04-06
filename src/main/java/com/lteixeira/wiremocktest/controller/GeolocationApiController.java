package com.lteixeira.wiremocktest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("geolocation")
public class GeolocationApiController {

    @Autowired
    private ExternalApiService externalApiService;

    @GetMapping
    public GeolocationResponse get() {
        GeolocationResponse geolocationResponse = externalApiService.get();
        if (Objects.nonNull(geolocationResponse)) {
            geolocationResponse.setIp("24.48.0.1");
        }
        return geolocationResponse;
    }
}
