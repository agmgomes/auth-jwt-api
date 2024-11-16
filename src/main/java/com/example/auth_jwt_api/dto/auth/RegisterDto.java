package com.example.auth_jwt_api.dto.auth;

import com.example.auth_jwt_api.entity.Role;

public record RegisterDto(String login,
                          String password,
                          Role role) {
}
