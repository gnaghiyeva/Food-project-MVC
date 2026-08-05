package org.example.food.dtos.chefdtos;

import lombok.Data;

import java.util.Date;

@Data
public class ChefDto {
    private Long id;
    private String fullName;
    private String position;
    private String description;
    private String photoUrl;
    private String xLink;
    private String facebookLink;
    private String instagramLink;
    private String linkedinLink;
    private Date createdAt;
}
