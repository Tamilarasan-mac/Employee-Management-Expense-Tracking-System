package com.example.expense_management.controller;

import com.example.expense_management.dto.ExpenseRequest;
import com.example.expense_management.dto.ExpenseResponse;
import com.example.expense_management.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(
            ExpenseService expenseService) {

        this.expenseService = expenseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseResponse createExpense(
            @Valid @RequestBody ExpenseRequest request) {

        return expenseService.createExpense(request);
    }

    @GetMapping
    public List<ExpenseResponse> getAllExpenses() {

        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ExpenseResponse getExpenseById(
            @PathVariable Long id) {

        return expenseService.getExpenseById(id);
    }

    @PutMapping("/{id}")
    public ExpenseResponse updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequest request) {

        return expenseService.updateExpense(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(
            @PathVariable Long id) {

        expenseService.deleteExpense(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ExpenseResponse> getExpensesByEmployee(
            @PathVariable Long employeeId) {

        return expenseService
                .getExpensesByEmployee(employeeId);
    }

    @GetMapping("/category/{categoryId}")
    public List<ExpenseResponse> getExpensesByCategory(
            @PathVariable Long categoryId) {

        return expenseService
                .getExpensesByCategory(categoryId);
    }
}