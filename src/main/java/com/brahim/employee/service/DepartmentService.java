package com.brahim.employee.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.brahim.employee.model.department.Department;
import com.brahim.employee.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public Optional<Department> getDepartment(Integer id) {
        return departmentRepository.findById(id);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department postDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department putDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department patchDepartment(Integer id, Map<String, Object> fields) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Department.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, department.get(), value);
                }
            });
            return departmentRepository.save(department.get());
        }
        return null;
    }

    public void deleteDepartment(Integer id) {
        try {
            departmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        }
    }

}
