package org.example.food.controller;

import org.example.food.dtos.contactformdtos.ContactFormCreateDto;
import org.example.food.dtos.contactformdtos.ContactFormDto;
import org.example.food.dtos.gallerydtos.GalleryDto;
import org.example.food.dtos.orderdtos.OrderCreateDto;
import org.example.food.model.ContactForm;
import org.example.food.service.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactFormController {

    @Autowired
    private ContactFormService contactFormService;

    @GetMapping("/admin/contact-form")
    public String form(Model model) {
        List<ContactFormDto> forms = contactFormService.getForms();
        model.addAttribute("forms", forms);
        return "dashboard/form/contact-form";
    }

    @PostMapping("/contact-form/create")
    public String addForm(@ModelAttribute ContactFormCreateDto contactFormCreateDto){
        contactFormService.addContactForm(contactFormCreateDto);
        return "redirect:/";
    }
}
