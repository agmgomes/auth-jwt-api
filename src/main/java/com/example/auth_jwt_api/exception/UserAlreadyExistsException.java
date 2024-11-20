package com.example.auth_jwt_api.exception;

import com.example.auth_jwt_api.dto.exception.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public class UserAlreadyExistsException extends ApiException {
    private final String username;

    public UserAlreadyExistsException(String username) {
        this.username = username;
    }

    @Override
    public ResponseEntity<ApiErrorResponse> ApiErrorException() {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.CONFLICT.value(),
                "User with login " + username + " already exists.",
                Instant.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
