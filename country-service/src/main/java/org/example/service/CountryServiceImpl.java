package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.CountryResponseDto;
import org.example.mapper.CountryMapper;
import org.example.persistence.CountryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryMapper countryMapper;
    private final CountryRepository countryRepository;

    @Override
    public CountryResponseDto getCountry(Integer phoneCode) {
        return countryMapper.toDto(countryRepository.findByPhoneCode(phoneCode));
    }
}
