package org.example.food.mapper;

import org.example.food.dtos.contactdtos.ContactCreateDto;
import org.example.food.dtos.contactdtos.ContactDto;
import org.example.food.dtos.contactdtos.ContactHomeDto;
import org.example.food.dtos.contactdtos.ContactUpdateDto;
import org.example.food.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    @Mapping(target = "id", ignore = true)
    Contact toEntity(ContactCreateDto contactCreateDto);

    ContactDto toDto(Contact contact);

    ContactHomeDto toHomeDto(Contact contact);

    ContactUpdateDto toUpdateDto(Contact contact);

    void updateEntityFromDto(ContactUpdateDto contactUpdateDto, @MappingTarget Contact contact);
}
