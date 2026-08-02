package org.example.food.dtos.aboutdtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AboutCreateDto {
    private String title;
    private String description;
    private String videoUrl;
    private MultipartFile photoFile;
}
