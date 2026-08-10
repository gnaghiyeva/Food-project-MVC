package org.example.food.controller;

import lombok.RequiredArgsConstructor;
import org.example.food.dtos.admindtos.AdminCreateDto;
import org.example.food.model.Role;
import org.example.food.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute(
                "admins",
                adminService.getAllAdmins()
        );

        return "dashboard/admin/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute(
                "admin",
                new AdminCreateDto()
        );

        model.addAttribute(
                "roles",
                Role.values()
        );

        return "dashboard/admin/create";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute AdminCreateDto adminCreateDto
    ) {

        adminService.createAdmin(adminCreateDto);

        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        adminService.deleteAdmin(id);

        return "redirect:/admin/users";
    }
}