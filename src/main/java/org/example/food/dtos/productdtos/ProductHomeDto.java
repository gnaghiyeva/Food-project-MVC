package org.example.food.dtos.productdtos;

import lombok.Data;
import org.example.food.dtos.categorydtos.CategoryDto;

@Data
public class ProductHomeDto {
    private String name;
    private String ingredient;
    private Long price;
    private String photoUrl;
    private CategoryDto category;
}
