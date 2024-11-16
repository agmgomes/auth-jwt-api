package com.example.auth_jwt_api.exception;

import com.example.auth_jwt_api.dto.exception.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public class TasksListIsEmpty extends ApiException{

    private final Long userId;

    public TasksListIsEmpty(Long userId) {
        this.userId = userId;
    }

    public TasksListIsEmpty() {
        this.userId = null;
    }

    @Override
    public ResponseEntity<ApiErrorResponse> ApiErrorException() {
        String message = (userId == null) ?
                "List of tasks is empty.": "List of tasks for user id:" + userId + " is empty.";

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                message,
                Instant.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
