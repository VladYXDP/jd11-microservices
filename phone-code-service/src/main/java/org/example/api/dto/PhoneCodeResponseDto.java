package org.example.api.dto;

import lombok.Builder;

@Builder(setterPrefix = "with")
public record PhoneCodeResponseDto(
        Integer id,
        Integer code
) {
}
