package com.example.expense_management.service;

import com.example.expense_management.dto.ExpenseRequest;
import com.example.expense_management.dto.ExpenseResponse;

import java.util.List;

public interface ExpenseService {

    ExpenseResponse createExpense(
            ExpenseRequest request);

    List<ExpenseResponse> getAllExpenses();

    ExpenseResponse getExpenseById(Long id);

    ExpenseResponse updateExpense(
            Long id,
            ExpenseRequest request);

    void deleteExpense(Long id);

    List<ExpenseResponse> getExpensesByEmployee(
            Long employeeId);

    List<ExpenseResponse> getExpensesByCategory(
            Long categoryId);
}