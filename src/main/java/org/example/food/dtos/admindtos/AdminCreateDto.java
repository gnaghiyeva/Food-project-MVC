package org.example.food.dtos.admindtos;

import lombok.Data;
import org.example.food.model.Role;

@Data
public class AdminCreateDto {

    private String fullName;

    private String username;

    private String password;

    private Role role;
}