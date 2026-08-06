package org.example.food.dtos.chefdtos;

import lombok.Data;

@Data
public class ChefHomeDto {
    private Long id;
    private String fullName;
    private String position;
    private String description;
    private String photoUrl;
    private String xLink;
    private String facebookLink;
    private String instagramLink;
    private String linkedinLink;
}
