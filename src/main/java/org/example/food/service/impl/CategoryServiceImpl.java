package org.example.food.service.impl;

import org.example.food.dtos.categorydtos.CategoryCreateDto;
import org.example.food.dtos.categorydtos.CategoryDto;
import org.example.food.dtos.categorydtos.CategoryHomeDto;
import org.example.food.dtos.categorydtos.CategoryUpdateDto;
import org.example.food.mapper.CategoryMapper;
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
    private CategoryMapper categoryMapper;

    @Override
    public void createCategory(CategoryCreateDto categoryCreateDto) {
        Category category = categoryMapper.toEntity(categoryCreateDto);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<CategoryDto> categories = categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
        return categories;
    }

    @Override
    public void updateCategory(CategoryUpdateDto categoryUpdateDto) {
        Category findCategory = categoryRepository.findById(categoryUpdateDto.getId()).orElseThrow();
        categoryMapper.updateEntityFromDto(categoryUpdateDto, findCategory);
        categoryRepository.save(findCategory);
    }

    @Override
    public CategoryUpdateDto findUpdatedCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        return categoryMapper.toUpdateDto(category);
    }

    @Override
    public void removeCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryHomeDto> getHomeCategories() {
        List<CategoryHomeDto> categories = categoryRepository.findAll().stream()
                .map(categoryMapper::toHomeDto)
                .collect(Collectors.toList());
        return categories;
    }
}
