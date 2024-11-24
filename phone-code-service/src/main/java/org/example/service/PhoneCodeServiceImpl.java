package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.PhoneCodeResponseDto;
import org.example.mapper.PhoneCodeMapper;
import org.example.persistence.PhoneCodeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneCodeServiceImpl implements PhoneCodeService {

    private final PhoneCodeMapper phoneCodeMapper;
    private final PhoneCodeRepository phoneCodeRepository;

    @Override
    public PhoneCodeResponseDto getPhoneCode(Integer phoneCodeId) {
        return phoneCodeMapper.toDto(phoneCodeRepository.findById(phoneCodeId).orElse(null));
    }
}
