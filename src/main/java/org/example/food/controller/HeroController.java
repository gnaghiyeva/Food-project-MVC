package org.example.food.controller;

import org.example.food.dtos.herodtos.HeroCreateDto;
import org.example.food.dtos.herodtos.HeroDto;
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
       return "redirect:/dashboard/hero";
    }
}
