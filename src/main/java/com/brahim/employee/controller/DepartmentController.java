package com.brahim.employee.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.brahim.employee.model.department.Department;
import com.brahim.employee.service.DepartmentService;
scence builder
@RestController
@RequestMapping(path = "/api/department", produces = "application/json")
@CrossOrigin(origins = "http://localhost:3000,http://localhost:8080")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

    @GetMapping(path = "/getDepartment/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id) {
        Optional<Department> department = departmentService.getDepartment(id);
        if (department.isPresent())
            return new ResponseEntity<>(department.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAll")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping(path = "/postDepartment", consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Department postDepartment(@RequestBody Department department) {
        return departmentService.postDepartment(department);
    }

    @PutMapping(path = "/putDepartment/{id}", consumes = "application/json")
    public Department putDepartment(@PathVariable Integer id, @RequestBody Department department) {
        department.setId(id);
        return departmentService.putDepartment(department);
    }

    @PatchMapping(path = "/patchDepartment/{id}", consumes = "application/json")
    public Department patchDepartment(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
        return departmentService.patchDepartment(id, fields);
    }

    @DeleteMapping(path = "/deleteDepartment/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
    }

}
