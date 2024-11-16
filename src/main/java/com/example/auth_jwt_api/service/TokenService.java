package com.example.auth_jwt_api.service;

import com.example.auth_jwt_api.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    TokenUtils tokenUtils;

    public String generateTokenForUser(String username, Long userId) {
        return tokenUtils.generateToken(username, userId);
    }

    public String getLoginFromToken(String token) {
        return tokenUtils.extractUsernameFromToken(token);
    }

    public boolean validateUserToken(String token, UserDetails userDetails) {
        String username = tokenUtils.extractUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !tokenUtils.isTokenExpired(token));
    }
}
