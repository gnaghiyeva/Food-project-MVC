package org.example.food.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.food.dtos.admindtos.AdminCreateDto;
import org.example.food.dtos.admindtos.AdminDto;
import org.example.food.model.Admin;
import org.example.food.repository.AdminRepository;
import org.example.food.service.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createAdmin(AdminCreateDto dto) {

        if (adminRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Bu username artıq mövcuddur");
        }

        Admin admin = new Admin();

        admin.setFullName(dto.getFullName());
        admin.setUsername(dto.getUsername());

        admin.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        admin.setRole(dto.getRole());
        admin.setEnabled(true);

        adminRepository.save(admin);
    }

    @Override
    public List<AdminDto> getAllAdmins() {

        return adminRepository.findAll()
                .stream()
                .map(admin -> {

                    AdminDto dto = new AdminDto();

                    dto.setId(admin.getId());
                    dto.setFullName(admin.getFullName());
                    dto.setUsername(admin.getUsername());
                    dto.setRole(admin.getRole());
                    dto.setEnabled(admin.isEnabled());

                    return dto;
                })
                .toList();
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}