package org.example.food.dtos.productdtos;

import lombok.Data;
import org.example.food.dtos.categorydtos.CategoryDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String ingredient;
    private Long price;
    private String photoUrl;
    private CategoryDto category;
    private Date createdDate;
    private Date updatedDate;
}
