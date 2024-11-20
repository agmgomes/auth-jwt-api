package com.example.auth_jwt_api.service.impl;

import com.example.auth_jwt_api.dto.auth.AuthRequestDto;
import com.example.auth_jwt_api.dto.auth.RegisterDto;
import com.example.auth_jwt_api.entity.Role;
import com.example.auth_jwt_api.entity.User;
import com.example.auth_jwt_api.exception.UserAlreadyExistsException;
import com.example.auth_jwt_api.repository.UserRepository;
import com.example.auth_jwt_api.security.UserDetailsImpl;
import com.example.auth_jwt_api.service.AuthenticationService;
import com.example.auth_jwt_api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;

    @Override
    public User save(RegisterDto registerDto) {

        if(userRepository.findByLogin(registerDto.login()).isPresent()) {
            throw new UserAlreadyExistsException(registerDto.login());
        }

        Role role = Role.valueOf(registerDto.role());
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        User newUser = new User(registerDto.login(), encryptedPassword, role);

        return userRepository.save(newUser);
    }

    @Override
    public String authenticate(AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.login(), authRequestDto.password()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getUserId();

        return tokenService.generateTokenForUser(authRequestDto.login(), userId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(String login) {
        return null;
    }
}
