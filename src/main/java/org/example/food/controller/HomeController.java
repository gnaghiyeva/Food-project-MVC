package org.example.food.controller;

import org.example.food.dtos.aboutdtos.AboutHomeDto;
import org.example.food.dtos.herodtos.HeroHomeDto;
import org.example.food.dtos.whyusdtos.WhyUsHomeDto;
import org.example.food.service.AboutService;
import org.example.food.service.HeroService;
import org.example.food.service.WhyUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private HeroService heroService;

    @Autowired
    private AboutService aboutService;

    @Autowired
    private WhyUsService whyUsService;

    @GetMapping("/")
    public String home(Model model){
        List<HeroHomeDto> homeHero = heroService.getHomeHero();
        List<AboutHomeDto> homeAbout = aboutService.getHomeAbout();
        List<WhyUsHomeDto> homeCards = whyUsService.getHomeCards();
        model.addAttribute("hero", homeHero);
        model.addAttribute("about", homeAbout);
        model.addAttribute("cards", homeCards);
        return "home";
    }
}
