package com.brahim.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.brahim.employee.model.employee.Employee;
import com.brahim.employee.service.EmployeeService;

@Component("employeeSecurity")
public class EmployeeSecurity {

    @Autowired
    private EmployeeService employeeService;

    public boolean isEmployeeOwner(Integer id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Employee employee = employeeService.getEmployee(id).orElse(null);
        if (employee == null) {
            return false; // Employee not found, deny access
        }

        return employee.getFirstname().equals(username);
    }
}