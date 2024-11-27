package org.example.api;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.PhoneCodeResponseDto;
import org.example.service.PhoneCodeService;
import org.example.service.PhoneCodeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/phone-code")
@RequiredArgsConstructor
public class PhoneCodeController {

    private final PhoneCodeService phoneCodeService;

    @GetMapping("/{phoneCodeId}")
    public ResponseEntity<PhoneCodeResponseDto> get(@PathVariable Integer phoneCodeId) {
        PhoneCodeResponseDto dto = phoneCodeService.getPhoneCode(phoneCodeId);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
