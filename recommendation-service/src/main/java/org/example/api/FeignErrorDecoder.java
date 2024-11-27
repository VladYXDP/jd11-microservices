package org.example.api;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 401 -> new ResponseStatusException(HttpStatusCode.valueOf(401));
            case 404 -> new ResponseStatusException(HttpStatusCode.valueOf(404));
            case 500 -> new ResponseStatusException(HttpStatusCode.valueOf(500));
            case 503 -> new ResponseStatusException(HttpStatusCode.valueOf(503));
            default -> null;
        };
    }
}
