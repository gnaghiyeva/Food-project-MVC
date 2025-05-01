package org.example.food.controller;

import org.example.food.dtos.aboutdtos.AboutCreateDto;
import org.example.food.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AboutController {
    @Autowired
    private AboutService aboutService;

    @GetMapping("/about")
    public String About(){
        return "home";
    }

    @GetMapping("/admin/about")
    public String about(){
        return "/dashboard/about/about";
    }
    @GetMapping("/admin/about/about-create")
    public String createAbout(){
        return "/dashboard/about/about-create";
    }

    @PostMapping("/admin/about/create")
    public String createAbout(@ModelAttribute AboutCreateDto aboutCreateDto){
        aboutService.createAbout(aboutCreateDto);
        return "redirect:/admin/about";
    }
}
