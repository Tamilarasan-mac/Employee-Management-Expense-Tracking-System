package com.example.expense_management.service;

import com.example.expense_management.dto.CategoryRequest;
import com.example.expense_management.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Long id);

    CategoryResponse updateCategory(
            Long id,
            CategoryRequest request);

    void deleteCategory(Long id);
}