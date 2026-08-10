package org.example.food.service;


import org.example.food.dtos.admindtos.AdminCreateDto;
import org.example.food.dtos.admindtos.AdminDto;

import java.util.List;

public interface AdminService {

    void createAdmin(AdminCreateDto adminCreateDto);

    List<AdminDto> getAllAdmins();

    void deleteAdmin(Long id);
}