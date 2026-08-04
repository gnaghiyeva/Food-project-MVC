package org.example.food.service;

import org.example.food.dtos.eventdtos.EventCreateDto;
import org.example.food.dtos.eventdtos.EventDto;
import org.example.food.dtos.eventdtos.EventHomeDto;
import org.example.food.dtos.eventdtos.EventUpdateDto;

import java.util.List;

public interface EventService {
    void addEvent(EventCreateDto eventCreateDto);
    List<EventDto> getEvents();
    void removeEvent(int id);
    void updateEvent(EventUpdateDto eventUpdateDto);
    EventUpdateDto findUpdatedEvent(int id);
    List<EventHomeDto> getHomeEvents();
}

