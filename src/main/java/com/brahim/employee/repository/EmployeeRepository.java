package com.brahim.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.brahim.employee.model.employee.Employee;
import java.util.List;


@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByFirstname(@Param("firstname") String firstname);
    List<Employee> findByLastname(@Param("lastname") String lastname);
    List<Employee> findByFunction(@Param("function") String function);
    List<Employee> findByFirstnameAndLastname(@Param("firstname") String firstname,@Param("lastname") String lastname);
}
