package org.example.food.controller;

import org.example.food.dtos.aboutdtos.AboutHomeDto;
import org.example.food.dtos.categorydtos.CategoryHomeDto;
import org.example.food.dtos.chefdtos.ChefHomeDto;
import org.example.food.dtos.eventdtos.EventDto;
import org.example.food.dtos.eventdtos.EventHomeDto;
import org.example.food.dtos.gallerydtos.GalleryHomeDto;
import org.example.food.dtos.herodtos.HeroHomeDto;
import org.example.food.dtos.productdtos.ProductHomeDto;
import org.example.food.dtos.testimonialdto.TestimonialHomeDto;
import org.example.food.dtos.whyusdtos.WhyUsHomeDto;
import org.example.food.service.*;
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

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ChefService chefService;

    @Autowired
    private GalleryService galleryService;

    @GetMapping("/")
    public String home(Model model){
        List<HeroHomeDto> homeHero = heroService.getHomeHero();
        List<AboutHomeDto> homeAbout = aboutService.getHomeAbout();
        List<WhyUsHomeDto> homeCards = whyUsService.getHomeCards();
        List<CategoryHomeDto> homeCategories = categoryService.getHomeCategories();
        List<ProductHomeDto> homeProducts = productService.getHomeProducts();
        List<TestimonialHomeDto> homeTestimonials = testimonialService.getHomeTestimonials();
        List<EventHomeDto> homeEvents = eventService.getHomeEvents();
        List<EventHomeDto> events = eventService.getHomeEvents();
        List<ChefHomeDto> homeChefs = chefService.getHomeChefs();
        List<GalleryHomeDto> homeGalleries = galleryService.getHomeGallery();
        System.out.println("HOME EVENTS SIZE: " + events.size());
        model.addAttribute("hero", homeHero);
        model.addAttribute("about", homeAbout);
        model.addAttribute("cards", homeCards);
        model.addAttribute("categories", homeCategories);
        model.addAttribute("products", homeProducts);
        model.addAttribute("testimonials", homeTestimonials);
        model.addAttribute("events", homeEvents);
        model.addAttribute("chefs",homeChefs);
        model.addAttribute("galleries", homeGalleries);

        return "home";
    }
}
