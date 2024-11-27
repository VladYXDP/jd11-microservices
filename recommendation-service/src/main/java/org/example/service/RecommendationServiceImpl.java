package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.CountryResponseDto;
import org.example.api.dto.PhoneCodeResponseDto;
import org.example.api.dto.RecommendationResponseDto;
import org.example.api.feign.CountryFeingClient;
import org.example.api.feign.PhoneCodeFeignClient;
import org.example.mapper.RecommendationMapper;
import org.example.persistence.RecommendationRepository;
import org.example.persistence.entity.Recommendation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final CountryFeingClient countryFeingClient;
    private final RecommendationMapper recommendationMapper;
    private final PhoneCodeFeignClient phoneCodeFeignClient;
    private final RecommendationRepository recommendationRepository;

    //todo сделать массовую выгрузку, а не бегать каждый раз в сервисы
    @Override
    public List<RecommendationResponseDto> getAllRecommendation() {
        List<RecommendationResponseDto> recommendationRespDto = new ArrayList<>();
        List<Recommendation> recommendations = recommendationRepository.findAll();
        recommendations.forEach(rec -> {
            RecommendationResponseDto recRespDto = recommendationMapper.toDto(rec);
            PhoneCodeResponseDto phoneCodeResponseDto = phoneCodeFeignClient.get(rec.getPhoneCodeId()).getBody();
            if (phoneCodeResponseDto != null) {
                CountryResponseDto countryResponseDto = countryFeingClient.get(phoneCodeResponseDto.getCode()).getBody();
                phoneCodeResponseDto.setCountry(countryResponseDto);
                recommendationMapper.setPhoneCode(recRespDto, phoneCodeResponseDto);
            }
            recommendationRespDto.add(recRespDto);
        });
        return recommendationRespDto;
    }

    @Override
    public RecommendationResponseDto getById(Long id) {
        Optional<Recommendation> recommendationOpt = recommendationRepository.findById(id);
        if (recommendationOpt.isPresent()) {
            return recommendationMapper.toDto(recommendationOpt.get());
        } else {
            throw new RuntimeException("Рекомендация с id " + id + " не найдена!");
        }
    }
}
