package org.example.mapper;

import org.example.api.dto.PhoneCodeResponseDto;
import org.example.persistence.entity.PhoneCode;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface PhoneCodeMapper {

    PhoneCodeResponseDto toDto(PhoneCode phoneCode);
}
