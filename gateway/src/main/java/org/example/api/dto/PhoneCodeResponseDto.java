package org.example.api.dto;

import lombok.Builder;

@Builder(setterPrefix = "with")
public record PhoneCodeResponseDto(
        Long id,
        Integer code,
        CountryResponseDto country
) {
}
