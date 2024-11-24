package org.example.api;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.RecommendationResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gateway/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    @GetMapping
    public ResponseEntity<List<RecommendationResponseDto>> getAll() {
        return null;
    }
}
