package org.example.api.dto;

import lombok.Builder;

@Builder(setterPrefix = "with")
public record CountryResponseDto(
        Long id,
        String countryName
){
}