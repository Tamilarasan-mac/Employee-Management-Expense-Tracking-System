package com.example.expense_management.dto;

public record CategoryResponse(
        Long id,
        String name,
        String description
) {
}