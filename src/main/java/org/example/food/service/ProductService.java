package org.example.food.service;

import org.example.food.dtos.productdtos.ProductCreateDto;

public interface ProductService {
    void addProduct(ProductCreateDto productCreateDto);
}
