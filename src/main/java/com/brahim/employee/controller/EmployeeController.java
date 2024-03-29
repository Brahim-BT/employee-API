package com.brahim.employee.controller;

import java.util.Map;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brahim.employee.model.employee.Employee;
import com.brahim.employee.service.EmployeeService;

@RestController
@RequestMapping(path = "/api/employee", produces = "application/json")
@CrossOrigin(origins = "http://localhost:3000,http://localhost:8080")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/getEmployee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        if (employee.isPresent())
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAll")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping(path = "/postEmployee", consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee postEmployee(@RequestBody Employee employee) {
        return employeeService.postEmployee(employee);
    }

    @PutMapping(path = "/putEmployee/{id}", consumes = "application/json")
    public Employee putEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeService.putEmployee(employee);
    }

    @PatchMapping(path = "/patchEmployee/{id}", consumes = "application/json")
    public Employee patchEmployee(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
        return employeeService.patchEmployee(id, fields);
    }

    @DeleteMapping(path = "/deleteEmployee/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }
}
