package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.api.dto.CountryResponseDto;
import org.example.api.dto.PhoneCodeResponseDto;
import org.example.api.dto.RecommendationResponseDto;
import org.example.api.dto.kafka.KafkaDtoModel;
import org.example.api.feign.CountryFeingClient;
import org.example.api.feign.PhoneCodeFeignClient;
import org.example.mapper.RecommendationMapper;
import org.example.persistence.RecommendationRepository;
import org.example.persistence.entity.Recommendation;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final String PHONE_CODE_KAFKA_KEY = "phone_code_kafka_key";

    private final ObjectMapper objectMapper;
    private final CountryFeingClient countryFeingClient;
    private final RecommendationMapper recommendationMapper;
    private final PhoneCodeFeignClient phoneCodeFeignClient;
    private final RecommendationRepository recommendationRepository;
    private final KafkaTemplate<String, KafkaDtoModel> kafkaTemplate;

    public List<RecommendationResponseDto> getAllRecommendationWithKafka() {
        List<RecommendationResponseDto> recommendationResponseDtos = new ArrayList<>();
        List<Recommendation> recommendations = recommendationRepository.findAll();
        Set<Integer> phoneCodeNumbers = new HashSet<>();
        recommendations.forEach(rec -> phoneCodeNumbers.add(rec.getPhoneCodeId()));

        try {
            KafkaDtoModel kafkaDtoModel = new KafkaDtoModel();
            kafkaDtoModel.setRequest(objectMapper.writeValueAsString(phoneCodeNumbers));
            SendResult<String, KafkaDtoModel> phoneCodeResult = kafkaTemplate.send("phone_code_get_event", PHONE_CODE_KAFKA_KEY, kafkaDtoModel).get();
        } catch (Exception e) {
            System.out.println("Ошибка получения данных от PhoneCodeService");
        }
        return recommendationResponseDtos;
    }

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
