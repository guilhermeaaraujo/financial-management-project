package com.guilherme.financialmanagement.domain.dto.request;

import com.guilherme.financialmanagement.domain.enums.UserRole;

public record RegisterUserRequest(String name, String email, String password, UserRole role) {
}
