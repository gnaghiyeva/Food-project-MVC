package org.example.food.controller;

import org.example.food.dtos.aboutdtos.AboutCreateDto;
import org.example.food.dtos.aboutdtos.AboutDto;
import org.example.food.dtos.aboutdtos.AboutUpdateDto;
import org.example.food.dtos.contactdtos.ContactCreateDto;
import org.example.food.dtos.contactdtos.ContactDto;
import org.example.food.dtos.contactdtos.ContactUpdateDto;
import org.example.food.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/admin/contact")
    public String contact(Model model){
        List<ContactDto> contact = contactService.getContact();
        model.addAttribute("contact", contact);
        return "/dashboard/contact/contact";
    }

    @GetMapping("/admin/contact/contact-create")
    public String createContact(){
        return "/dashboard/contact/contact-create";
    }

    @PostMapping("/admin/contact/create")
    public String createContact(@ModelAttribute ContactCreateDto contactCreateDto){
        contactService.createContact(contactCreateDto);
        return "redirect:/admin/contact";
    }

    @GetMapping("/admin/contact/contact-edit/{id}")
    public String updateContact(@ModelAttribute @PathVariable Long id, Model model){
        ContactUpdateDto contactUpdateDto = contactService.findUpdatedContact(id);
        model.addAttribute("contact", contactUpdateDto);
        return "dashboard/contact/contact-edit";
    }

    @PostMapping("/admin/contact/update")
    public String updateContact(@ModelAttribute ContactUpdateDto contactUpdateDto){
        contactService.updatedContact(contactUpdateDto);
        return "redirect:/admin/contact";
    }

}
