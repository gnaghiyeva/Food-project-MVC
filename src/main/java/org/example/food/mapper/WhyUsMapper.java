package org.example.food.mapper;

import org.example.food.dtos.whyusdtos.WhyUsCreateDto;
import org.example.food.dtos.whyusdtos.WhyUsDto;
import org.example.food.dtos.whyusdtos.WhyUsHomeDto;
import org.example.food.dtos.whyusdtos.WhyUsUpdateDto;
import org.example.food.model.WhyUs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WhyUsMapper {

    @Mapping(target = "id", ignore = true)
    // isMain null gələrsə, əvvəlki koddakı kimi false-a çevrilir
    @Mapping(target = "isMain", expression = "java(whyUsCreateDto.getIsMain() != null && whyUsCreateDto.getIsMain())")
    WhyUs toEntity(WhyUsCreateDto whyUsCreateDto);

    WhyUsDto toDto(WhyUs whyUs);

    WhyUsHomeDto toHomeDto(WhyUs whyUs);

    WhyUsUpdateDto toUpdateDto(WhyUs whyUs);

    void updateEntityFromDto(WhyUsUpdateDto whyUsUpdateDto, @MappingTarget WhyUs whyUs);
}
