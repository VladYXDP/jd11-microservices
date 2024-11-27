package org.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecommendationResponseDto {

    private Long id;
    private String fullName;
    private String company;
    private String position;
    private String phoneNumber;
    private String email;
    private String linkedIn;
    private String telegram;
    private String viber;
    private String whatsApp;
    private String recommendations;
    private PhoneCodeResponseDto phoneCodeResponseDto;
}

