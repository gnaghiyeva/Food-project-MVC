package org.example.food.controller;

import org.example.food.dtos.eventdtos.EventCreateDto;
import org.example.food.dtos.eventdtos.EventDto;
import org.example.food.dtos.eventdtos.EventUpdateDto;
import org.example.food.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/admin/event")
    public String event(Model model) {
        List<EventDto> events = eventService.getEvents();
        model.addAttribute("events", events);
        return "dashboard/event/event";
    }

    @GetMapping("/admin/event/event-create")
    public String addTestimonial() {
        return "dashboard/event/event-create";
    }

    @PostMapping("/admin/event/create")
    public String addEvent(@ModelAttribute EventCreateDto eventCreateDto){
        eventService.addEvent(eventCreateDto);
        return "redirect:/admin/event";
    }

    @GetMapping("/admin/event/event-edit/{id}")
    public String updateEvent(@ModelAttribute @PathVariable int id, Model model){
        EventUpdateDto eventUpdateDto = eventService.findUpdatedEvent(id);
        model.addAttribute("event", eventUpdateDto);
        return "dashboard/event/event-edit";
    }

    @PostMapping("/admin/event/update")
    public String updateEvent(@ModelAttribute EventUpdateDto eventUpdateDto) {
        eventService.updateEvent(eventUpdateDto);
        return "redirect:/admin/event";
    }

    @GetMapping("/admin/event/remove/{id}")
    public String removeEvent(@ModelAttribute @PathVariable int id){
        eventService.removeEvent(id);
        return "redirect:/admin/event";
    }


}
