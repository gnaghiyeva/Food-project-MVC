package org.example.food.controller;

import org.example.food.dtos.herodtos.HeroHomeDto;
import org.example.food.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private HeroService heroService;

    @GetMapping("/")
    public String home(Model model){
        List<HeroHomeDto> homeHero = heroService.getHomeHero();
        model.addAttribute("hero", homeHero);
        return "home";
    }
}
