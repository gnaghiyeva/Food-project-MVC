package org.example.food.mapper;

import org.example.food.dtos.categorydtos.CategoryCreateDto;
import org.example.food.dtos.categorydtos.CategoryDto;
import org.example.food.dtos.categorydtos.CategoryHomeDto;
import org.example.food.dtos.categorydtos.CategoryUpdateDto;
import org.example.food.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryCreateDto categoryCreateDto);

    CategoryDto toDto(Category category);

    CategoryHomeDto toHomeDto(Category category);

    CategoryUpdateDto toUpdateDto(Category category);

    @Mapping(target = "products", ignore = true)
    void updateEntityFromDto(CategoryUpdateDto categoryUpdateDto, @MappingTarget Category category);
}

