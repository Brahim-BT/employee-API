package com.brahim.employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brahim.employee.controller.EmployeeController;

@SpringBootTest
class EmployeeApplicationTests {

	@Autowired
	private EmployeeController employeeController;

	@Test
	@DisplayName("First example test case")
	void contextLoads() {
		assertThat(employeeController).isNotNull();
	}

}
