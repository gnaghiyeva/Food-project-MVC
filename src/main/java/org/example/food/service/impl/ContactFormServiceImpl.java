package org.example.food.service.impl;

import org.example.food.dtos.chefdtos.ChefDto;
import org.example.food.dtos.contactformdtos.ContactFormCreateDto;
import org.example.food.dtos.contactformdtos.ContactFormDto;
import org.example.food.mapper.ContactFormMapper;
import org.example.food.model.ContactForm;
import org.example.food.repository.ContactFormRepository;
import org.example.food.service.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactFormServiceImpl implements ContactFormService {

    @Autowired
    private ContactFormRepository contactFormRepository;

    @Autowired
    private ContactFormMapper contactFormMapper;

    @Override
    public void addContactForm(ContactFormCreateDto contactFormCreateDto) {
        ContactForm contactForm = contactFormMapper.toEntity(contactFormCreateDto);
        contactFormRepository.save(contactForm);
    }

    @Override
    public List<ContactFormDto> getForms() {
        List<ContactFormDto> forms = contactFormRepository.findAll().stream()
                .map(contactFormMapper::toDto)
                .collect(Collectors.toList());
        return forms;
    }
}
