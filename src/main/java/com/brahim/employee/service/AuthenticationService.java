package com.brahim.employee.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brahim.employee.dto.LoginResponseDTO;
import com.brahim.employee.model.user.AppUser;
import com.brahim.employee.model.user.Role;
import com.brahim.employee.repository.AppUserRepository;
import com.brahim.employee.repository.RoleRepository;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public AppUser registAppUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return appUserRepository.save(new AppUser(null, username, encodedPassword, authorities));
    }

    public LoginResponseDTO loginUser(String username, String password) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            String token = tokenService.generateJwt(auth);
            AppUser user = appUserRepository.findByUsername(username).get();
            return new LoginResponseDTO(user, token);
        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");
        }
    }
}