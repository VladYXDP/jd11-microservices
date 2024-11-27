package org.example.api.feign;

import org.example.api.dto.CountryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("COUNTRY-SERVICE")
public interface CountryFeingClient {

    @GetMapping("/api/v1/country/{phoneCode}")
    ResponseEntity<CountryResponseDto> get(@PathVariable Integer phoneCode);
}
