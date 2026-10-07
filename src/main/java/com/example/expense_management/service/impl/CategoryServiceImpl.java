package com.example.expense_management.service.impl;

import com.example.expense_management.dto.CategoryRequest;
import com.example.expense_management.dto.CategoryResponse;
import com.example.expense_management.entity.Category;
import com.example.expense_management.exception.ResourceNotFoundException;
import com.example.expense_management.mapper.CategoryMapper;
import com.example.expense_management.repository.CategoryRepository;
import com.example.expense_management.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            CategoryMapper categoryMapper) {

        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponse createCategory(
            CategoryRequest request) {

        if (categoryRepository
                .existsByNameIgnoreCase(request.name())) {

            throw new RuntimeException(
                    "Category already exists");
        }

        Category category =
                categoryMapper.toEntity(request);

        Category savedCategory =
                categoryRepository.save(category);

        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {

        Category category =
                categoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"));

        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(
            Long id,
            CategoryRequest request) {

        Category category =
                categoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"));

        category.setName(request.name());
        category.setDescription(request.description());

        Category updatedCategory =
                categoryRepository.save(category);

        return categoryMapper.toResponse(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {

        if (!categoryRepository.existsById(id)) {

            throw new ResourceNotFoundException("Category not found");
        }

        categoryRepository.deleteById(id);
    }
}