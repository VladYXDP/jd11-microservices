package org.example.api.dto;

import lombok.Builder;

@Builder(setterPrefix = "with")
public record RecommendationResponseDto(
        Long id,
        String fullName,
        String company,
        String position,
        String phoneNumber,
        String email,
        String linkedIn,
        String telegram,
        String viber,
        String whatsApp,
        String recommendations,
        PhoneCodeResponseDto phoneCodeResponseDto) {
}
