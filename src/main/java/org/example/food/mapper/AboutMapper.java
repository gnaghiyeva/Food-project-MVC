package org.example.food.mapper;

import org.example.food.dtos.aboutdtos.AboutCreateDto;
import org.example.food.dtos.aboutdtos.AboutDto;
import org.example.food.dtos.aboutdtos.AboutHomeDto;
import org.example.food.dtos.aboutdtos.AboutUpdateDto;
import org.example.food.model.About;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AboutMapper {

    // photoFile -> photoUrl-a faylın diskə yazılmasından sonra service qatında set olunur, ona görə ignore edilir
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photoUrl", ignore = true)
    About toEntity(AboutCreateDto aboutCreateDto);

    AboutDto toDto(About about);

    AboutHomeDto toHomeDto(About about);

    @Mapping(target = "photoFile", ignore = true)
    AboutUpdateDto toUpdateDto(About about);

    // photoUrl yalnız yeni fayl yüklənərsə service qatında dəyişdirilir
    @Mapping(target = "photoUrl", ignore = true)
    void updateEntityFromDto(AboutUpdateDto aboutUpdateDto, @MappingTarget About about);
}

