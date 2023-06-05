package com.brahim.employee.model.employee;

import java.util.Date;
import com.brahim.employee.model.department.Department;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer age;
    private Date birthDate;
    private String address;
    private String function;
    private Date engagedDate;
    private FamilySituation familySituation;
    private AdministrativeSituation administrativeSituation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departmentId")
    private Department department;
}
