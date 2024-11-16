package com.example.auth_jwt_api.service;


import com.example.auth_jwt_api.dto.task.CreateTaskDto;
import com.example.auth_jwt_api.entity.Task;

import java.util.List;

public interface TaskService {
    Task createTask(CreateTaskDto taskDto);

    List<Task> findAllTasks();

    List<Task> findAllUserTasks();
}
