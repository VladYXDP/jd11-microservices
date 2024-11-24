package org.example.api.dto;

import lombok.Builder;

@Builder(setterPrefix = "with")
public record CountryResponseDto(
        Integer id,
        Integer phoneCode,
        String countryName
) {
}
