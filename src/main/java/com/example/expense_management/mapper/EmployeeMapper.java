package com.example.expense_management.mapper;

import com.example.expense_management.dto.EmployeeRequest;
import com.example.expense_management.dto.EmployeeResponse;
import com.example.expense_management.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {

        return Employee.builder()
                .name(request.name())
                .email(request.email())
                .department(request.department())
                .designation(request.designation())
                .salary(request.salary())
                .build();
    }

    public EmployeeResponse toResponse(Employee employee) {

        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getDesignation(),
                employee.getSalary()
        );
    }
}