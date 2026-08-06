package org.example.food.controller;

import org.example.food.dtos.eventdtos.EventCreateDto;
import org.example.food.dtos.eventdtos.EventDto;
import org.example.food.dtos.eventdtos.EventUpdateDto;
import org.example.food.dtos.gallerydtos.GalleryCreateDto;
import org.example.food.dtos.gallerydtos.GalleryDto;
import org.example.food.dtos.gallerydtos.GalleryUpdateDto;
import org.example.food.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GalleryController {
    @Autowired
    private GalleryService galleryService;

    @GetMapping("/admin/gallery")
    public String gallery(Model model) {
        List<GalleryDto> galeries = galleryService.getGallery();
        model.addAttribute("galleries", galeries);
        return "dashboard/gallery/gallery";
    }

    @GetMapping("/admin/gallery/gallery-create")
    public String addPhoto() {
        return "dashboard/gallery/gallery-create";
    }

    @PostMapping("/admin/gallery/create")
    public String addPhoto(@ModelAttribute GalleryCreateDto galleryCreateDto){
        galleryService.addPhoto(galleryCreateDto);
        return "redirect:/admin/gallery";
    }

    @GetMapping("/admin/gallery/gallery-edit/{id}")
    public String updatePhoto(@ModelAttribute @PathVariable int id, Model model){
        GalleryUpdateDto galleryUpdateDto = galleryService.findUpdatedPhoto(id);
        model.addAttribute("gallery", galleryUpdateDto);
        return "dashboard/gallery/gallery-edit";
    }

    @PostMapping("/admin/gallery/update")
    public String updatePhoto(@ModelAttribute GalleryUpdateDto galleryUpdateDto) {
        galleryService.updatePhoto(galleryUpdateDto);
        return "redirect:/admin/gallery";
    }

    @GetMapping("/admin/gallery/remove/{id}")
    public String removePhoto(@ModelAttribute @PathVariable int id){
        galleryService.removePhoto(id);
        return "redirect:/admin/gallery";
    }

}
