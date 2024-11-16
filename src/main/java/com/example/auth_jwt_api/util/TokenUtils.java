package com.example.auth_jwt_api.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class TokenUtils {
    @Value("${api.security.jwt.secret}")
    private String jwtSecret;

    public String generateToken(String username, Long userId) {
        try {
            return JWT.create()
                    .withIssuer("auth-jwt-api")
                    .withClaim("userId", userId)
                    .withSubject(username)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 1000 * 15))
                    .sign(Algorithm.HMAC256(jwtSecret));

        } catch (JWTCreationException e) {
           throw new RuntimeException("JWT Creation Exception" + e);
        }
    }

    public <T> T extractClaimFromToken(String token, Function<DecodedJWT, T> claimsResolver) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer("auth-jwt-api")
                    .build();

            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            return claimsResolver.apply(decodedJWT);
        } catch (JWTVerificationException e) {
            // Invalid Token
            return null;
        }
    }

    public String extractUsernameFromToken(String token) {
        return extractClaimFromToken(token, DecodedJWT::getSubject);
    }

    public Date extractExpirationFromToken(String token) {
        return extractClaimFromToken(token, DecodedJWT::getExpiresAt);
    }

    public boolean isTokenExpired(String token) {
        return extractExpirationFromToken(token).before(new Date());
    }
}
