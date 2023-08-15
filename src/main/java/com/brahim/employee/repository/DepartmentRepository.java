package com.brahim.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.brahim.employee.model.department.Department;


@RepositoryRestResource
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    Optional<Department> findByName(String name);
}
