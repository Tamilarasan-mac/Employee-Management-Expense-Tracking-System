package com.example.expense_management.service.impl;

import com.example.expense_management.dto.EmployeeRequest;
import com.example.expense_management.dto.EmployeeResponse;
import com.example.expense_management.entity.Employee;
import com.example.expense_management.exception.ResourceNotFoundException;
import com.example.expense_management.mapper.EmployeeMapper;
import com.example.expense_management.repository.EmployeeRepository;
import com.example.expense_management.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            EmployeeMapper employeeMapper) {

        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {

        Employee employee = employeeMapper.toEntity(request);

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(savedEmployee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id));

        return employeeMapper.toResponse(employee);
    }
    @Override
    public EmployeeResponse updateEmployee(
            Long id,
            EmployeeRequest request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id));

        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setDepartment(request.department());
        employee.setDesignation(request.designation());
        employee.setSalary(request.salary());

        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }

        employeeRepository.deleteById(id);
    }
}