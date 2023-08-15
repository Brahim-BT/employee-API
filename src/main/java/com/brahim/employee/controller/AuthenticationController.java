package com.brahim.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brahim.employee.dto.LoginResponseDTO;
import com.brahim.employee.dto.RegistrationDTO;
import com.brahim.employee.model.user.AppUser;
import com.brahim.employee.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*") // this not the best practice by putting the * symbol there, because this just
                  // make this work
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public AppUser registAppUser(@RequestBody RegistrationDTO body) {
        return authenticationService.registAppUser(body.username(), body.password());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginuser(@RequestBody RegistrationDTO body) {
        return authenticationService.loginUser(body.username(), body.password());
    }
}
