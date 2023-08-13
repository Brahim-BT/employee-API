package com.brahim.employee;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.brahim.employee.model.department.Department;
import com.brahim.employee.repository.DepartmentRepository;

@DataJpaTest
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void saveDepartment() {
        departmentRepository.save(new Department(null, "Electrecity", "Address 1", null));
        assertThat(departmentRepository.findByName("Electrecity").isPresent()).isTrue();
    }

    @Test
    void deleteDepartments() {
        departmentRepository.save(new Department(null, "Geometry", "Address 2", null));
        departmentRepository.deleteAll();
        assertThat(departmentRepository.count()).isEqualTo(0);
    }
}
