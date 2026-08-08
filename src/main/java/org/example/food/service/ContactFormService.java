package org.example.food.service;

import org.example.food.dtos.contactformdtos.ContactFormCreateDto;
import org.example.food.dtos.contactformdtos.ContactFormDto;

import java.util.List;

public interface ContactFormService {
    void addContactForm(ContactFormCreateDto contactFormCreateDto);
    List<ContactFormDto> getForms();
}
