package com.example.auth_jwt_api.dto.exception;

import java.time.Instant;

public record ApiErrorResponse(int status,
                               Object message,
                               Instant timestamp) {
}
