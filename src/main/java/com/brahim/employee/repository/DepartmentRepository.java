package com.brahim.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brahim.employee.model.department.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    
}
