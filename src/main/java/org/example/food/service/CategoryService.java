package org.example.food.service;

import org.example.food.dtos.categorydtos.CategoryCreateDto;
import org.example.food.dtos.categorydtos.CategoryDto;
import org.example.food.dtos.categorydtos.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryCreateDto categoryCreateDto);
    List<CategoryDto> getCategories();
    void updateCategory(CategoryUpdateDto categoryUpdateDto);
    CategoryUpdateDto findUpdatedCategory(Long id);
    void removeCategory(Long categoryId);
}
