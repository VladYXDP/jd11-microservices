package org.example.api;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.RecommendationResponseDto;
import org.example.service.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping
    public ResponseEntity<List<RecommendationResponseDto>> getAll() {
        return new ResponseEntity<>(recommendationService.getAllRecommendationWithKafka(), HttpStatus.OK);
    }

    @GetMapping("/{recId}")
    public ResponseEntity<RecommendationResponseDto> getById(@PathVariable Long recId) {
        return new ResponseEntity<>(recommendationService.getById(recId), HttpStatus.OK);
    }
}
