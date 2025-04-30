package org.example.food.dtos.herodtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class HeroUpdateDto {
    private Long id;
    private String title;
    private String subTitle;
    private String videoUrl;
    private String photoUrl;
    private MultipartFile photoFile;
}
