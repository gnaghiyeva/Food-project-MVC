package org.example.food.mapper;

import org.example.food.dtos.eventdtos.EventCreateDto;
import org.example.food.dtos.eventdtos.EventDto;
import org.example.food.dtos.eventdtos.EventHomeDto;
import org.example.food.dtos.eventdtos.EventUpdateDto;
import org.example.food.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface EventMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photoUrl", ignore = true)
    Event toEntity(EventCreateDto eventCreateDto);
    EventDto toDto(Event events);
    EventHomeDto toHomeDto(Event event);

    @Mapping(target = "photoFile", ignore = true)
    EventUpdateDto toUpdateDto(Event event);


    @Mapping(target = "photoUrl", ignore = true)
    void updateEntityFromDto(EventUpdateDto eventUpdateDto, @MappingTarget Event event);
}
