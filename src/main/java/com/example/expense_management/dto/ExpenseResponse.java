package com.example.expense_management.dto;

import java.time.LocalDate;

public record ExpenseResponse(

        Long id,

        String title,

        Double amount,

        LocalDate expenseDate,

        String description,

        Long employeeId,

        String employeeName,

        Long categoryId,

        String categoryName
) {
}