package org.example.mapper;

import org.example.api.dto.RecommendationResponseDto;
import org.example.persistence.entity.Recommendation;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface RecommendationMapper {

    List<RecommendationResponseDto> toDto(List<Recommendation> recommendations);
}
