package com.example.auth_jwt_api.service.impl;

import com.example.auth_jwt_api.dto.task.CreateTaskDto;
import com.example.auth_jwt_api.entity.Task;
import com.example.auth_jwt_api.exception.TasksListIsEmpty;
import com.example.auth_jwt_api.repository.TaskRepository;
import com.example.auth_jwt_api.security.UserDetailsImpl;
import com.example.auth_jwt_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task createTask(CreateTaskDto taskDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Task newTask = new Task(taskDto.title(), taskDto.description(), userDetails.getUser());

        return taskRepository.save(newTask);
    }

    @Override
    public List<Task> findAllUserTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Long userId = userDetails.getUserId();
        List<Task> tasks = taskRepository.findByUserId(userId);

        if(tasks.isEmpty()) {
            throw new TasksListIsEmpty(userId);
        }

        return taskRepository.findByUserId(userId);
    }

    @Override
    public List<Task> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        if(tasks.isEmpty()) {
            throw new TasksListIsEmpty();
        }
        return taskRepository.findAll();
    }
}
