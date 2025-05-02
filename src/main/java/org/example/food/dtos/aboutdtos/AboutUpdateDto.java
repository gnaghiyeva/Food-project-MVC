package org.example.food.dtos.aboutdtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AboutUpdateDto {
    private Long id;
    private String title;
    private String description;
    private String videoUrl;
    private String photoUrl;
    private MultipartFile photoFile;
}
