package org.example.food.mapper;

import org.example.food.dtos.gallerydtos.GalleryCreateDto;
import org.example.food.dtos.gallerydtos.GalleryDto;
import org.example.food.dtos.gallerydtos.GalleryHomeDto;
import org.example.food.dtos.gallerydtos.GalleryUpdateDto;
import org.example.food.model.Gallery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GalleryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photoUrl", ignore = true)
    Gallery toEntity(GalleryCreateDto galleryCreateDto);
    GalleryDto toDto(Gallery galleries);
    GalleryHomeDto toHomeDto(Gallery gallery);

    @Mapping(target = "photoFile", ignore = true)
    GalleryUpdateDto toUpdateDto(Gallery gallery);

    @Mapping(target = "photoUrl", ignore = true)
    void updateEntityFromDto(GalleryUpdateDto galleryUpdateDto, @MappingTarget Gallery gallery);
}
