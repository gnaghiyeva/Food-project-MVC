package org.example.food.service.impl;

import org.example.food.dtos.categorydtos.CategoryCreateDto;
import org.example.food.dtos.categorydtos.CategoryDto;
import org.example.food.dtos.categorydtos.CategoryHomeDto;
import org.example.food.dtos.categorydtos.CategoryUpdateDto;
import org.example.food.model.Category;
import org.example.food.repository.CategoryRepository;
import org.example.food.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createCategory(CategoryCreateDto categoryCreateDto) {
        Category category = modelMapper.map(categoryCreateDto, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<CategoryDto> categories = categoryRepository.findAll().stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categories;
    }

    @Override
    public void updateCategory(CategoryUpdateDto categoryUpdateDto) {
    Category findCategory = categoryRepository.findById(categoryUpdateDto.getId()).orElseThrow();
    findCategory.setName(categoryUpdateDto.getName());
    categoryRepository.save(findCategory);
    }

    @Override
    public CategoryUpdateDto findUpdatedCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        CategoryUpdateDto categoryUpdateDto = modelMapper.map(category, CategoryUpdateDto.class);
        return categoryUpdateDto;
    }

    @Override
    public void removeCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryHomeDto> getHomeCategories() {
        List<CategoryHomeDto> categories = categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryHomeDto.class)).collect(Collectors.toList());
        return categories;
    }
}
