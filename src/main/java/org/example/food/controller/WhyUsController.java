package org.example.food.controller;

import org.example.food.dtos.whyusdtos.WhyUsCreateDto;
import org.example.food.dtos.whyusdtos.WhyUsDto;
import org.example.food.dtos.whyusdtos.WhyUsUpdateDto;
import org.example.food.service.WhyUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WhyUsController {
    @Autowired
    private WhyUsService whyUsService;

    @GetMapping("/admin/why-us")
    public String whyUs(Model model){
        List<WhyUsDto> cards = whyUsService.getCards();
        model.addAttribute("cards", cards);
        return "/dashboard/why-us/why-us";
    }
    @GetMapping("/admin/why-us/why-us-create")
    public String addCard(Model model){
        model.addAttribute("whyUsCreateDto", new WhyUsCreateDto());
        return "/dashboard/why-us/why-us-create";
    }

    @PostMapping("/admin/why-us/create")
    public String addCard(@ModelAttribute WhyUsCreateDto whyUsCreateDto){
        System.out.println("Checkbox selected? isMain = " + whyUsCreateDto.getIsMain());
        whyUsService.createCard(whyUsCreateDto);
        return "redirect:/admin/why-us";
    }

    @GetMapping("/admin/why-us/why-us-edit/{id}")
    public String updatedCard(@PathVariable Long id, Model model){
        WhyUsUpdateDto whyUsUpdateDto = whyUsService.findUpdatedCard(id);
        model.addAttribute("cards", whyUsUpdateDto);
        return "dashboard/why-us/why-us-edit";
    }

    @PostMapping("/admin/why-us/update")
    public String updateCard(@ModelAttribute WhyUsUpdateDto whyUsUpdateDto){
        whyUsService.updatedCard(whyUsUpdateDto);
        return "redirect:/admin/why-us";
    }
}
