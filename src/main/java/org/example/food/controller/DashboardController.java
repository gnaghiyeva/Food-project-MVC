package org.example.food.controller;

import lombok.RequiredArgsConstructor;
import org.example.food.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ChefRepository chefRepository;
    private final EventRepository eventRepository;
    private final ContactFormRepository contactFormRepository;
    private final AdminRepository adminRepository;

    @GetMapping("/admin")
    public String admin(Model model) {

        model.addAttribute(
                "productCount",
                productRepository.count()
        );

        model.addAttribute(
                "orderCount",
                orderRepository.count()
        );

        model.addAttribute(
                "chefCount",
                chefRepository.count()
        );

        model.addAttribute(
                "eventCount",
                eventRepository.count()
        );

        model.addAttribute(
                "messageCount",
                contactFormRepository.count()
        );

        model.addAttribute(
                "adminCount",
                adminRepository.count()
        );

        return "dashboard/home";
    }
}