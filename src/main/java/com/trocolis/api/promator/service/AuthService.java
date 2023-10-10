package com.trocolis.api.promator.service;

import com.trocolis.api.promator.infra.error.exception.BusinessException;
import com.trocolis.api.promator.model.dto.auth.request.LoginRequest;
import com.trocolis.api.promator.model.dto.user.UserAuthDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthService(@Autowired AuthenticationManager authenticationManager,
                       @Autowired TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public String login(LoginRequest request) throws BusinessException {
        LOGGER.info("Login request: {}", request.email());
        var userNamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        // TODO: Verificar melhor forma de tratar erros de altenticação
        var authentication = authenticationManager.authenticate(userNamePassword);

        String token = tokenService.generateToken((UserAuthDTO) authentication.getPrincipal());

        LOGGER.info("Successfully generated token");
        return token;
    }
}
