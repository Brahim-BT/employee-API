package com.brahim.employee;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

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
                field.setAccessible(true);
                ReflectionUtils.setField(field, employee.get(), value);
            });
            return employeeRepository.save(employee.get());
        }
        return null;
    }
}
