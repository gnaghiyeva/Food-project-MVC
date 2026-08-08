package org.example.food.mapper;

import org.example.food.dtos.contactformdtos.ContactFormCreateDto;
import org.example.food.dtos.contactformdtos.ContactFormDto;
import org.example.food.model.ContactForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactFormMapper {
    @Mapping(target = "id", ignore = true)
    ContactForm toEntity(ContactFormCreateDto contactFormCreateDto);
    ContactFormDto toDto(ContactForm contactForm);
}
