package com.example.expense_management.service;

import com.example.expense_management.dto.EmployeeRequest;
import com.example.expense_management.dto.EmployeeResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest request);

    Page<EmployeeResponse> getAllEmployees(
            int page,
            int size,
            String sortBy,
            String direction);

    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);

    void deleteEmployee(Long id);

    public List<EmployeeResponse> getEmployeesByDepartment(
            String department) {

        return employeeRepository
                .findByDepartmentIgnoreCase(department)
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }


}