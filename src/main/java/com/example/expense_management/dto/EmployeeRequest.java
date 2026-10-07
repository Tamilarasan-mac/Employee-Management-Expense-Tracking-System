package com.example.expense_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Department is required")
        String department,

        @NotBlank(message = "Designation is required")
        String designation,

        @NotNull(message = "Salary is required")
        Double salary
) {
}