package com.guilherme.financialmanagement.domain.dto;

import com.guilherme.financialmanagement.domain.enums.UserRole;

public record UserDTO(Long id, String name, String email, UserRole role) {
}
