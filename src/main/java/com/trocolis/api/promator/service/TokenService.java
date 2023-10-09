package com.trocolis.api.promator.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.trocolis.api.promator.model.dto.user.UserAuthDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    private final String secret;

    public TokenService(@Value("${api.security.token.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(UserAuthDTO user) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        return JWT.create()
                .withIssuer("auth.api")
                .withSubject(user.getUsername())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        return JWT.require(algorithm)
                .withIssuer("auth.api")
                .build()
                .verify(token)
                .getSubject();

    }

    private Instant getExpirationDate() {
        return Instant.now().plus(1, ChronoUnit.HOURS);
    }
}
