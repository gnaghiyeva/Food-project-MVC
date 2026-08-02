package org.example.food.controller;

import org.example.food.dtos.aboutdtos.AboutCreateDto;
import org.example.food.dtos.aboutdtos.AboutDto;
import org.example.food.dtos.aboutdtos.AboutUpdateDto;
import org.example.food.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AboutController {
    @Autowired
    private AboutService aboutService;

//    @GetMapping("/about")
//    public String About(){
//        return "home";
//    }

    @GetMapping("/admin/about")
    public String about(Model model){
        List<AboutDto> about = aboutService.getAbout();
        model.addAttribute("about", about);
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

    @GetMapping("/admin/about/about-edit/{id}")
    public String updateAbout(@ModelAttribute @PathVariable Long id, Model model){
        AboutUpdateDto aboutUpdateDto = aboutService.findUpdatedAbout(id);
        model.addAttribute("about", aboutUpdateDto);
        return "dashboard/about/about-edit";
    }

    @PostMapping("/admin/about/update")
    public String updateAbout(@ModelAttribute AboutUpdateDto aboutUpdateDto){
        aboutService.updatedAbout(aboutUpdateDto);
        return "redirect:/admin/about";
    }
}
