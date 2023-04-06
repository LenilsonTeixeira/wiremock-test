package com.lteixeira.wiremocktest.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "externalApi", url = "${base.url.external-service}")
public interface ExternalApiService {

    @GetMapping("/v3/81d77d34-f29d-4bb3-93de-6d30e557beb6")
    GeolocationResponse get();
}
