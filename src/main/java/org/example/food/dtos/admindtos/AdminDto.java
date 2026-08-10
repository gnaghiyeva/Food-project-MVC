package org.example.food.dtos.admindtos;

import lombok.Data;
import org.example.food.model.Role;

@Data
public class AdminDto {

    private Long id;

    private String fullName;

    private String username;

    private Role role;

    private boolean enabled;
}