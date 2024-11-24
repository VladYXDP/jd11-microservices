package org.example.service;

import org.example.api.dto.CountryResponseDto;

public interface CountryService {

    CountryResponseDto getCountry(Integer phoneCode);
}
