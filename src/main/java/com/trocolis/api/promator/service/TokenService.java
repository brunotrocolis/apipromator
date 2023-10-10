package com.trocolis.api.promator.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.trocolis.api.promator.infra.error.code.ErrorCode;
import com.trocolis.api.promator.infra.error.exception.BusinessException;
import com.trocolis.api.promator.model.dto.user.UserAuthDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    private final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    private final String secret;

    public TokenService(@Value("${api.security.token.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(UserAuthDTO user) throws BusinessException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
            return JWT.create()
                    .withIssuer("auth.api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            LOGGER.error("Erro ao gerar token:", e);
            throw  new BusinessException(ErrorCode.ERROR_GENERATING_TOKEN, e);
        }
    }

    public String validateToken(String token) {
        // TODO: Verificar melhor forma de tratar erro de token inválido
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
