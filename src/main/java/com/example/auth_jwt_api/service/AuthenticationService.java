package com.example.auth_jwt_api.service;

import com.example.auth_jwt_api.dto.auth.AuthRequestDto;
import com.example.auth_jwt_api.dto.auth.RegisterDto;
import com.example.auth_jwt_api.entity.User;

import java.util.List;

public interface AuthenticationService {
    User save(RegisterDto registerDto);

    String authenticate(AuthRequestDto authRequestDto);

    List<User> findAll();

    User findOne(String login);
}
