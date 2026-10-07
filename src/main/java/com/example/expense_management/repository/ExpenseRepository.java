package com.example.expense_management.repository;

import com.example.expense_management.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository
        extends JpaRepository<Expense, Long> {

    List<Expense> findByEmployeeId(Long employeeId);

    List<Expense> findByCategoryId(Long categoryId);
}