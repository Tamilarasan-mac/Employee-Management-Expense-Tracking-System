package com.example.expense_management.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(

        @NotBlank(message = "Category name is required")
        String name,

        String description
) {
}