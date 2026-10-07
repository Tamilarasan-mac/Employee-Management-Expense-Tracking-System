package com.example.expense_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record ExpenseRequest(

        @NotBlank(message = "Title is required")
        String title,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than zero")
        Double amount,

        @NotNull(message = "Expense date is required")
        LocalDate expenseDate,

        String description,

        @NotNull(message = "Employee ID is required")
        Long employeeId,

        @NotNull(message = "Category ID is required")
        Long categoryId
) {
}