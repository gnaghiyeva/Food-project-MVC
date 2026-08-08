package org.example.food.service;

import org.example.food.dtos.contactdtos.ContactCreateDto;
import org.example.food.dtos.contactdtos.ContactDto;
import org.example.food.dtos.contactdtos.ContactHomeDto;
import org.example.food.dtos.contactdtos.ContactUpdateDto;

import java.util.List;

public interface ContactService {
    void createContact(ContactCreateDto contactCreateDto);
    List<ContactDto> getContact();
    void updatedContact(ContactUpdateDto contactUpdateDto);
    ContactUpdateDto findUpdatedContact(Long id);
    List<ContactHomeDto> getHomeContact();
}
