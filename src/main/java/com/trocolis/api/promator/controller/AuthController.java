package com.trocolis.api.promator.controller;

import com.trocolis.api.promator.model.domain.user.UserStatusDomain;
import com.trocolis.api.promator.model.dto.auth.request.ConfirmationRequest;
import com.trocolis.api.promator.model.dto.auth.request.LoginRequest;
import com.trocolis.api.promator.model.dto.auth.request.RegisterRequest;
import com.trocolis.api.promator.model.dto.user.UserAuthDTO;
import com.trocolis.api.promator.model.entity.Credential;
import com.trocolis.api.promator.model.entity.User;
import com.trocolis.api.promator.model.repository.CredentialRepository;
import com.trocolis.api.promator.model.repository.UserRepository;
import com.trocolis.api.promator.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl authService;

    public AuthController(@Autowired AuthenticationManager authenticationManager,
                          @Autowired UserRepository userRepository,
                          @Autowired CredentialRepository credentialRepository,
                          @Autowired BCryptPasswordEncoder passwordEncoder,
                          @Autowired UserDetailsServiceImpl authService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.credentialRepository = credentialRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var authentication = authenticationManager.authenticate(userNamePassword);

        var token = authService.generateToken((UserAuthDTO) authentication.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
        var user = userRepository.findByEmail(request.email());
        if (user != null) {
            return ResponseEntity.status(400)
                    .body(String.format("Já existe um usuário %s com este e-mail", user.getStatus().getDescription()));
        }
        user = userRepository.save(new User(request.name(), request.email(), request.birthDate()));
        return ResponseEntity.ok(user.getId().toString());
    }

    @PostMapping("/confirmation")
    public ResponseEntity<UUID> confirmation(@RequestBody @Valid ConfirmationRequest request) {
        var user = userRepository.findById(request.userId());
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        user.get().setStatus(UserStatusDomain.ACTIVE);

        var out = userRepository.save(user.get());

        var encryptedPassword = passwordEncoder.encode(request.password());
        credentialRepository.save(new Credential(request.userId(), encryptedPassword));

        return ResponseEntity.ok(out.getId());
    }
}
