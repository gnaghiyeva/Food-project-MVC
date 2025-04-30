package org.example.food.controller;

import org.example.food.dtos.herodtos.HeroCreateDto;
import org.example.food.dtos.herodtos.HeroDto;
import org.example.food.dtos.herodtos.HeroUpdateDto;
import org.example.food.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HeroController {
    @Autowired
    private HeroService heroService;

    @GetMapping("/hero")
    public String Hero(){
        return "home";
    }

    @GetMapping("/admin/hero")
    public String category(Model model){
        List<HeroDto> hero = heroService.getHero();
        model.addAttribute("hero", hero);
        return "/dashboard/hero/hero";
    }

    @GetMapping("/admin/hero/hero-create")
    public String createHero(){
        return "/dashboard/hero/hero-create";
    }

    @PostMapping("/admin/hero/create")
    public String createHero(@ModelAttribute HeroCreateDto heroCreateDto){
       heroService.createHero(heroCreateDto);
       return "redirect:/admin/hero";
    }

    @GetMapping("/admin/hero/hero-edit/{id}")
    public String updateHero(@ModelAttribute @PathVariable Long id, Model model){
        HeroUpdateDto heroUpdateDto = heroService.findUpdateHero(id);
        model.addAttribute("hero", heroUpdateDto);
        return "dashboard/hero/hero-edit";
    }

    @PostMapping("/admin/hero/update")
    public String updateHero(@ModelAttribute HeroUpdateDto heroUpdateDto) {
        heroService.updatedHero(heroUpdateDto);
        return "redirect:/admin/hero";
    }

}
