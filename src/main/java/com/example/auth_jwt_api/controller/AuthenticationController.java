package com.example.auth_jwt_api.controller;

import com.example.auth_jwt_api.dto.auth.AuthRequestDto;
import com.example.auth_jwt_api.dto.auth.AuthResponseDto;
import com.example.auth_jwt_api.dto.auth.RegisterDto;
import com.example.auth_jwt_api.dto.auth.UserDto;
import com.example.auth_jwt_api.entity.User;
import com.example.auth_jwt_api.repository.UserRepository;
import com.example.auth_jwt_api.security.UserDetailsImpl;
import com.example.auth_jwt_api.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto authRequestDto) {
        String token = authenticationService.authenticate(authRequestDto);
        return ResponseEntity.ok(new AuthResponseDto(authRequestDto.login(), token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterDto registerDto) {
        User newUser = authenticationService.save(registerDto);
        return ResponseEntity.ok(newUser);
    }

    //Endpoints for testing roles
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/role")
    public UserDto roleValidation() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        User user = userDetails.getUser();
        return new UserDto(user.getId(), user.getLogin(), user.getRole());
    }
}
