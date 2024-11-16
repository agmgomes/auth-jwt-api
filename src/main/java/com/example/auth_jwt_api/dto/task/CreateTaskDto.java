package com.example.auth_jwt_api.dto.task;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskDto(@NotBlank String title,
                            @NotBlank String description) {
}
