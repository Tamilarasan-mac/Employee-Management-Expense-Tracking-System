package com.example.expense_management.dto;

public record EmployeeResponse(
        Long id,
        String name,
        String email,
        String department,
        String designation,
        Double salary
) {
}