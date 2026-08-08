package org.example.food.service.impl;

import org.example.food.dtos.categorydtos.CategoryDto;
import org.example.food.dtos.categorydtos.CategoryHomeDto;
import org.example.food.dtos.contactdtos.ContactCreateDto;
import org.example.food.dtos.contactdtos.ContactDto;
import org.example.food.dtos.contactdtos.ContactHomeDto;
import org.example.food.dtos.contactdtos.ContactUpdateDto;
import org.example.food.mapper.ContactMapper;
import org.example.food.model.Category;
import org.example.food.model.Contact;
import org.example.food.repository.ContactRepository;
import org.example.food.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public void createContact(ContactCreateDto contactCreateDto) {
        Contact contact = contactMapper.toEntity(contactCreateDto);
        contactRepository.save(contact);
    }

    @Override
    public List<ContactDto> getContact() {
        List<ContactDto> contacts = contactRepository.findAll().stream()
                .map(contactMapper::toDto)
                .collect(Collectors.toList());
        return contacts;
    }

    @Override
    public void updatedContact(ContactUpdateDto contactUpdateDto) {
        Contact findContact = contactRepository.findById(contactUpdateDto.getId()).orElseThrow();
        contactMapper.updateEntityFromDto(contactUpdateDto, findContact);
        contactRepository.save(findContact);
    }

    @Override
    public ContactUpdateDto findUpdatedContact(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow();
        System.out.println(contact);
        return contactMapper.toUpdateDto(contact);
    }

    @Override
    public List<ContactHomeDto> getHomeContact() {
        List<ContactHomeDto> contacts = contactRepository.findAll().stream()
                .map(contactMapper::toHomeDto)
                .collect(Collectors.toList());
        return contacts;
    }
}
