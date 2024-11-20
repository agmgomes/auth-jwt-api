package com.example.auth_jwt_api.dto.auth;

import com.example.auth_jwt_api.entity.Role;
import com.example.auth_jwt_api.validation.EnumValid;
import jakarta.validation.constraints.NotBlank;

public record RegisterDto(@NotBlank String login,
                          @NotBlank String password,
                          @NotBlank @EnumValid(enumClass = Role.class,
                          message = "Role must be one of the valid values: USER, ADMIN")
                          String role) {
}
