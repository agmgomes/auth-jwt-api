package com.example.auth_jwt_api.dto.exception;

import java.time.Instant;
import java.util.List;

public record ApiErrorListResponse(int status,
                                   List<ErrorDetail> errorDetails,
                                   Instant timestamp) {
}
