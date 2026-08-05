package org.example.food.mapper;

import org.example.food.dtos.chefdtos.ChefCreateDto;
import org.example.food.dtos.chefdtos.ChefDto;
import org.example.food.dtos.chefdtos.ChefHomeDto;
import org.example.food.dtos.chefdtos.ChefUpdateDto;
import org.example.food.model.Chef;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChefMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photoUrl", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Chef toEntity(ChefCreateDto chefCreateDto);
    ChefDto toDto(Chef chefs);
    ChefHomeDto toHomeDto(Chef chef);

    @Mapping(target = "photoFile", ignore = true)
    ChefUpdateDto toUpdateDto(Chef chef);

    @Mapping(target = "photoUrl", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(ChefUpdateDto chefUpdateDto, @MappingTarget Chef chef);
}
