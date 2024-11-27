package org.example.api.feign;

import org.example.api.dto.PhoneCodeResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PHONE-CODE-SERVICE")
public interface PhoneCodeFeignClient {

    @GetMapping("/api/v1/phone-code/{phoneCodeId}")
    ResponseEntity<PhoneCodeResponseDto> get(@PathVariable Integer phoneCodeId);
}
