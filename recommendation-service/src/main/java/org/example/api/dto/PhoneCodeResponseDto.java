package org.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhoneCodeResponseDto {

    private Long id;
    private Integer code;
    private CountryResponseDto country;
}
