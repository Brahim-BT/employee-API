package com.brahim.employee.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.brahim.employee.model.employee.Employee;
import com.brahim.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployee(Integer id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee postEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee putEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee patchEmployee(Integer id, Map<String, Object> fields) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Employee.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, employee.get(), value);
                }
            });
            return employeeRepository.save(employee.get());
        }
        return null;
    }
    

    public void deleteEmployee(Integer id) {
        try {
            employeeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        }
    }
}
