package com.example.expense_management.controller;

import com.example.expense_management.dto.EmployeeRequest;
import com.example.expense_management.dto.EmployeeResponse;
import com.example.expense_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        return employeeService.createEmployee(request);
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(
            @PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        return employeeService.updateEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);
    }
}