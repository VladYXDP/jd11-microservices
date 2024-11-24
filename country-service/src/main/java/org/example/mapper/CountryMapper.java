package org.example.mapper;

import org.example.api.dto.CountryResponseDto;
import org.example.persistence.entity.Country;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface CountryMapper {

    CountryResponseDto toDto(Country country);
}
