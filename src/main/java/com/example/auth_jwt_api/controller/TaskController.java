package com.example.auth_jwt_api.controller;

import com.example.auth_jwt_api.dto.task.CreateTaskDto;
import com.example.auth_jwt_api.entity.Task;
import com.example.auth_jwt_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody CreateTaskDto taskDto) {
        Task newTask = taskService.createTask(taskDto);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getUserTasks() {
        return ResponseEntity.ok(taskService.findAllUserTasks());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Task>> listAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }
}
