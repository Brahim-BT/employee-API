package com.brahim.employee.dto;

import com.brahim.employee.model.user.AppUser;

public record LoginResponseDTO(AppUser appUser, String jwt) {
}
