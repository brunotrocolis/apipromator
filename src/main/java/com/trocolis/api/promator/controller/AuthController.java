package com.trocolis.api.promator.controller;

import com.trocolis.api.promator.model.dto.auth.request.ConfirmationRequest;
import com.trocolis.api.promator.model.dto.auth.request.LoginRequest;
import com.trocolis.api.promator.model.dto.auth.request.RegisterRequest;
import com.trocolis.api.promator.service.AuthService;
import com.trocolis.api.promator.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(@Autowired UserService userService,
                          @Autowired AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UUID> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(userService.geristerUser(request));
    }

    @PostMapping("/confirmation")
    public ResponseEntity<UUID> confirmation(@RequestBody @Valid ConfirmationRequest request) {
        return ResponseEntity.ok(userService.activateUser(request));
    }
}
