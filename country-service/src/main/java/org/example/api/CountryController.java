package org.example.api;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.CountryResponseDto;
import org.example.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/{phoneCode}")
    public ResponseEntity<CountryResponseDto> get(@PathVariable Integer phoneCode) {
        return new ResponseEntity<>(countryService.getCountry(phoneCode), HttpStatus.OK);
    }
}
