package org.example.food.dtos.aboutdtos;

import lombok.Data;

@Data
public class AboutDto {
    private Long id;
    private String title;
    private String description;
    private String videoUrl;
    private String photoUrl;
}
