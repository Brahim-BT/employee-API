package com.brahim.employee;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.brahim.employee.model.user.AppUser;
import com.brahim.employee.model.user.Role;
import com.brahim.employee.repository.AppUserRepository;
import com.brahim.employee.repository.RoleRepository;

@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, AppUserRepository appUserRepository, PasswordEncoder passwordEncoder){
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole= roleRepository.save(new Role(1, "ADMIN"));
			roleRepository.save(new Role(2, "USER"));
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			AppUser adminUser = new AppUser(1, "admin", passwordEncoder.encode("password"), roles);
			appUserRepository.save(adminUser);
		};
	}
}
