package org.example.food.service;

import org.example.food.dtos.productdtos.ProductCreateDto;
import org.example.food.dtos.productdtos.ProductDto;

import java.util.List;

public interface ProductService {
    void addProduct(ProductCreateDto productCreateDto);
    List<ProductDto> getProducts();
}
