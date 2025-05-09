package org.example.food.service.impl;

import org.example.food.dtos.categorydtos.CategoryCreateDto;
import org.example.food.dtos.categorydtos.CategoryDto;
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
}
