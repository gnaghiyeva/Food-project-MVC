package org.example.food.controller;

import org.example.food.dtos.categorydtos.CategoryCreateDto;
import org.example.food.dtos.categorydtos.CategoryDto;
import org.example.food.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/category")
    public String category(Model model){
        List<CategoryDto> categoryDtoList = categoryService.getCategories();
        model.addAttribute("categories",categoryDtoList);
        return "dashboard/category/category";
    }

    @GetMapping("/admin/category/category-create")
    public String addCategory(){
        return "dashboard/category/category-create";
    }

    @PostMapping("/admin/category/create")
    public String addCategory(@ModelAttribute CategoryCreateDto categoryCreateDto){
        categoryService.createCategory(categoryCreateDto);
        return "redirect:/admin/category";
    }

}
