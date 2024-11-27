package org.example.mapper;

import org.example.api.dto.PhoneCodeResponseDto;
import org.example.api.dto.RecommendationResponseDto;
import org.example.persistence.entity.Recommendation;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true), uses = PhoneCodeResponseDto.class)
public interface RecommendationMapper {

    RecommendationResponseDto toDto(Recommendation recommendation);

    @Mapping(source = "phoneCodeResponseDto", target = "recommendationResponseDto.phoneCodeResponseDto")
    RecommendationResponseDto setPhoneCode(@MappingTarget RecommendationResponseDto recommendationResponseDto,
                                           PhoneCodeResponseDto phoneCodeResponseDto);
}
