package org.example.food.dtos.herodtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class HeroCreateDto {
    String title;
    String subTitle;
    String videoUrl;
    private MultipartFile photoFile;

}
