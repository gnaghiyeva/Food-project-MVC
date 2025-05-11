package org.example.food.dtos.productdtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductCreateDto {
    private String name;
    private String ingredient;
    private Long price;
    private MultipartFile photoFile;
    private Long categoryId;
}
