package com.brahim.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brahim.employee.model.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
