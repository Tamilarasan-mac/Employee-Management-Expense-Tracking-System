package com.example.expense_management.mapper;

import com.example.expense_management.dto.CategoryRequest;
import com.example.expense_management.dto.CategoryResponse;
import com.example.expense_management.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest request) {

        return Category.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    public CategoryResponse toResponse(Category category) {

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}