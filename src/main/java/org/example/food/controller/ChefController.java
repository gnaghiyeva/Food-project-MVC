package org.example.food.controller;

import org.example.food.dtos.categorydtos.CategoryDto;
import org.example.food.dtos.chefdtos.ChefCreateDto;
import org.example.food.dtos.chefdtos.ChefDto;
import org.example.food.dtos.chefdtos.ChefUpdateDto;
import org.example.food.dtos.productdtos.ProductUpdateDto;
import org.example.food.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ChefController {
    @Autowired
    private ChefService chefService;

    @GetMapping("/admin/chef")
    public String event(Model model) {
        List<ChefDto> chefs = chefService.getChefs();
        model.addAttribute("chefs", chefs);
        return "dashboard/chef/chef";
    }

    @GetMapping("/admin/chef/chef-create")
    public String addChef() {
        return "dashboard/chef/chef-create";
    }

    @PostMapping("/admin/chef/create")
    public String addChef(@ModelAttribute ChefCreateDto chefCreateDto){
        chefService.addChef(chefCreateDto);
        return "redirect:/admin/chef";
    }

    @GetMapping("/admin/chef/chef-edit/{id}")
    public String updateChef(@ModelAttribute @PathVariable Long id, Model model){
        ChefUpdateDto chefUpdateDto = chefService.findUpdatedChef(Math.toIntExact(id));
        model.addAttribute("chef", chefUpdateDto);
        return "dashboard/chef/chef-edit";
    }

    @PostMapping("/admin/chef/update")
    public String updateHero(@ModelAttribute ChefUpdateDto chefUpdateDto) {
        chefService.updateChef(chefUpdateDto);
        return "redirect:/admin/chef";
    }

    @GetMapping("/admin/chef/remove/{id}")
    public String removeChef(@ModelAttribute @PathVariable int id){
        chefService.removeChef(id);
        return "redirect:/admin/chef";
    }
}
