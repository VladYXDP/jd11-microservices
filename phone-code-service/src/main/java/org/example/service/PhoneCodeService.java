package org.example.service;

import org.example.api.dto.PhoneCodeResponseDto;

public interface PhoneCodeService {

    PhoneCodeResponseDto getPhoneCode(Integer phoneCodeId);
}
