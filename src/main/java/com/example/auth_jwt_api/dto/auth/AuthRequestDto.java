package com.example.auth_jwt_api.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDto(@NotBlank String login,
                             @NotBlank String password) {
}
