package org.example.service;

import org.example.api.dto.RecommendationResponseDto;

import java.util.List;

public interface RecommendationService {

    List<RecommendationResponseDto> getAllRecommendationWithKafka();

    List<RecommendationResponseDto> getAllRecommendation();

    RecommendationResponseDto getById(Long id);
}
