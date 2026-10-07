package com.example.expense_management.mapper;

import com.example.expense_management.dto.ExpenseResponse;
import com.example.expense_management.entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public ExpenseResponse toResponse(Expense expense) {

        return new ExpenseResponse(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getExpenseDate(),
                expense.getDescription(),
                expense.getEmployee().getId(),
                expense.getEmployee().getName(),
                expense.getCategory().getId(),
                expense.getCategory().getName()
        );
    }
}