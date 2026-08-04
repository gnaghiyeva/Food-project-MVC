package org.example.food.mapper;

import org.example.food.dtos.herodtos.HeroCreateDto;
import org.example.food.dtos.herodtos.HeroDto;
import org.example.food.dtos.herodtos.HeroHomeDto;
import org.example.food.dtos.herodtos.HeroUpdateDto;
import org.example.food.model.Hero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HeroMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photoUrl", ignore = true)
    Hero toEntity(HeroCreateDto heroCreateDto);

    HeroDto toDto(Hero hero);

    HeroHomeDto toHomeDto(Hero hero);

    @Mapping(target = "photoFile", ignore = true)
    HeroUpdateDto toUpdateDto(Hero hero);

    @Mapping(target = "photoUrl", ignore = true)
    void updateEntityFromDto(HeroUpdateDto heroUpdateDto, @MappingTarget Hero hero);
}
