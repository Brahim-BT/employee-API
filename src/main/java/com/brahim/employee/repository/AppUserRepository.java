package com.brahim.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.brahim.employee.model.user.AppUser;



@RepositoryRestResource(exported = false) // We don't want the users to be exposed as an endpoint by Rest Data
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
}
