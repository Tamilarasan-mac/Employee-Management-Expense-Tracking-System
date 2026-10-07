package com.example.expense_management.service.impl;

import com.example.expense_management.dto.ExpenseRequest;
import com.example.expense_management.dto.ExpenseResponse;
import com.example.expense_management.entity.Category;
import com.example.expense_management.entity.Employee;
import com.example.expense_management.entity.Expense;
import com.example.expense_management.exception.ResourceNotFoundException;
import com.example.expense_management.mapper.ExpenseMapper;
import com.example.expense_management.repository.CategoryRepository;
import com.example.expense_management.repository.EmployeeRepository;
import com.example.expense_management.repository.ExpenseRepository;
import com.example.expense_management.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final EmployeeRepository employeeRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseServiceImpl(
            ExpenseRepository expenseRepository,
            EmployeeRepository employeeRepository,
            CategoryRepository categoryRepository,
            ExpenseMapper expenseMapper) {

        this.expenseRepository = expenseRepository;
        this.employeeRepository = employeeRepository;
        this.categoryRepository = categoryRepository;
        this.expenseMapper = expenseMapper;
    }

    @Override
    public ExpenseResponse createExpense(
            ExpenseRequest request) {

        Employee employee =
                employeeRepository.findById(request.employeeId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found"));

        Category category =
                categoryRepository.findById(request.categoryId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"));

        Expense expense = Expense.builder()
                .title(request.title())
                .amount(request.amount())
                .expenseDate(request.expenseDate())
                .description(request.description())
                .employee(employee)
                .category(category)
                .build();

        Expense savedExpense =
                expenseRepository.save(expense);

        return expenseMapper.toResponse(savedExpense);
    }

    @Override
    public List<ExpenseResponse> getAllExpenses() {

        return expenseRepository.findAll()
                .stream()
                .map(expenseMapper::toResponse)
                .toList();
    }

    @Override
    public ExpenseResponse getExpenseById(Long id) {

        Expense expense =
                expenseRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Expense not found"));

        return expenseMapper.toResponse(expense);
    }

    @Override
    public ExpenseResponse updateExpense(
            Long id,
            ExpenseRequest request) {

        Expense expense =
                expenseRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Expense not found"));

        Employee employee =
                employeeRepository.findById(request.employeeId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found"));

        Category category =
                categoryRepository.findById(request.categoryId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"));

        expense.setTitle(request.title());
        expense.setAmount(request.amount());
        expense.setExpenseDate(request.expenseDate());
        expense.setDescription(request.description());
        expense.setEmployee(employee);
        expense.setCategory(category);

        Expense updatedExpense =
                expenseRepository.save(expense);

        return expenseMapper.toResponse(updatedExpense);
    }

    @Override
    public void deleteExpense(Long id) {

        if (!expenseRepository.existsById(id)) {

            throw new ResourceNotFoundException("Expense not found");
        }

        expenseRepository.deleteById(id);
    }

    @Override
    public List<ExpenseResponse> getExpensesByEmployee(
            Long employeeId) {

        return expenseRepository
                .findByEmployeeId(employeeId)
                .stream()
                .map(expenseMapper::toResponse)
                .toList();
    }

    @Override
    public List<ExpenseResponse> getExpensesByCategory(
            Long categoryId) {

        return expenseRepository
                .findByCategoryId(categoryId)
                .stream()
                .map(expenseMapper::toResponse)
                .toList();
    }
}