package org.example.food.service;

import org.example.food.dtos.productdtos.ProductCreateDto;
import org.example.food.dtos.productdtos.ProductDto;
import org.example.food.dtos.productdtos.ProductHomeDto;
import org.example.food.dtos.productdtos.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    void addProduct(ProductCreateDto productCreateDto);
    List<ProductDto> getProducts();
    void removeProduct(Long id);
    void updateProduct(ProductUpdateDto productUpdateDto);
    ProductUpdateDto findUpdatedProduct(Long id);
    List<ProductHomeDto> getHomeProducts();
}
