package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.RecommendationResponseDto;
import org.example.mapper.RecommendationMapper;
import org.example.persistence.RecommendationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationMapper recommendationMapper;
    private final RecommendationRepository recommendationRepository;

    @Override
    public List<RecommendationResponseDto> getAllRecommendation() {
        return recommendationMapper.toDto(recommendationRepository.findAll());
    }
}
