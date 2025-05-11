package org.example.food.controller;

import org.example.food.dtos.aboutdtos.AboutCreateDto;
import org.example.food.dtos.categorydtos.CategoryDto;
import org.example.food.dtos.herodtos.HeroDto;
import org.example.food.dtos.productdtos.ProductCreateDto;
import org.example.food.dtos.productdtos.ProductDto;
import org.example.food.service.CategoryService;
import org.example.food.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/product")
    public String product(Model model){
        List<ProductDto> productList = productService.getProducts();
        model.addAttribute("products", productList);
        return "/dashboard/product/product";
    }
    @GetMapping("/admin/product/product-create")
    public String createProduct(Model model){
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);

        return "/dashboard/product/product-create";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(@ModelAttribute ProductCreateDto productCreateDto){
        productService.addProduct(productCreateDto);
        return "redirect:/admin/product";
    }


}
