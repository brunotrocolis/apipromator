package com.trocolis.api.promator.service;

import com.trocolis.api.promator.model.dto.auth.request.LoginRequest;
import com.trocolis.api.promator.model.dto.user.UserAuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthService(@Autowired AuthenticationManager authenticationManager,
                       @Autowired TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public String login(LoginRequest request) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var authentication = authenticationManager.authenticate(userNamePassword);

        return tokenService.generateToken((UserAuthDTO) authentication.getPrincipal());
    }
}
