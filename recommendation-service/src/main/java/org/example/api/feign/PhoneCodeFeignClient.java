package org.example.api.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.example.api.dto.PhoneCodeResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PHONE-CODE-SERVICE")
public interface PhoneCodeFeignClient {

    @GetMapping("/api/v1/phone-code/{phoneCodeId}")
    @CircuitBreaker(name = "phone-code-service", fallbackMethod = "phoneCodeServiceFallback")
    @Retry(name = "phone-code-service", fallbackMethod = "phoneCodeServiceFallback")
    ResponseEntity<PhoneCodeResponseDto> get(@PathVariable Integer phoneCodeId);

    default ResponseEntity<PhoneCodeResponseDto> phoneCodeServiceFallback(Integer phoneCodeId, Throwable throwable) {
        System.out.println(phoneCodeId);
        System.out.println(throwable.getMessage());
        return new ResponseEntity<>(new PhoneCodeResponseDto(1L, 1000, null), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
