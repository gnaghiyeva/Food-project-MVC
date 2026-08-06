package org.example.food.dtos.gallerydtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class GalleryCreateDto {
    private MultipartFile photoFile;
}
