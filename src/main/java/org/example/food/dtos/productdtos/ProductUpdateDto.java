package org.example.food.dtos.productdtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductUpdateDto {
    private Long id;
    private String name;
    private String ingredient;
    private String price;
    private String photoUrl;
    private MultipartFile photoFile;
    private Long categoryId;
}
