package org.example.food.controller;

import org.example.food.dtos.categorydtos.CategoryDto;
import org.example.food.dtos.productdtos.ProductUpdateDto;
import org.example.food.dtos.testimonialdto.TestimonialCreateDto;
import org.example.food.dtos.testimonialdto.TestimonialDto;
import org.example.food.dtos.testimonialdto.TestimonialUpdateDto;
import org.example.food.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class TestimonialController {
    @Autowired
    private TestimonialService testimonialService;

    @GetMapping("/admin/testimonial")
    public String testimonial(Model model){
        List<TestimonialDto> testimonials = testimonialService.getTestimonial();
        model.addAttribute("testimonials", testimonials);
        return "dashboard/testimonial/testimonial";
    }

    @GetMapping("/admin/testimonial/testimonial-create")
    public String addTestimonial(){
        return "dashboard/testimonial/testimonial-create";
    }

    @PostMapping("/admin/testimonial/create")
    public String addTestimonial(@ModelAttribute TestimonialCreateDto testimonialCreateDto){
        testimonialService.addTestimonial(testimonialCreateDto);
        return "redirect:/admin/testimonial";
    }

    @GetMapping("/admin/testimonial/testimonial-edit/{id}")
    public String updateTestimonial(@ModelAttribute @PathVariable Long id, Model model){
        TestimonialUpdateDto testimonialUpdateDto = testimonialService.findUpdatedTestimonial(id);
        model.addAttribute("testimonial", testimonialUpdateDto);
        return "dashboard/testimonial/testimonial-edit";
    }

    @PostMapping("/admin/testimonial/update")
    public String updateTestimonial(@ModelAttribute TestimonialUpdateDto testimonialUpdateDto) {
        testimonialService.updateTestimonial(testimonialUpdateDto);
        return "redirect:/admin/testimonial";
    }

    @GetMapping("/admin/testimonial/remove/{id}")
    public String removeTestimonial(@ModelAttribute @PathVariable Long id){
        testimonialService.removeTestimonial(id);
        return "redirect:/admin/testimonial";
    }
}
