package org.example.config;

import feign.codec.ErrorDecoder;
import org.example.api.FeignErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ErrorDecoder createFeignClientDecoder() {
        return new FeignErrorDecoder();
    }
}
