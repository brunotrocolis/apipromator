package com.trocolis.api.promator.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.trocolis.api.promator.model.domain.user.CredentialStatusDomain;
import com.trocolis.api.promator.model.domain.user.UserStatusDomain;
import com.trocolis.api.promator.model.dto.user.UserAuthDTO;
import com.trocolis.api.promator.model.entity.User;
import com.trocolis.api.promator.model.repository.CredentialRepository;
import com.trocolis.api.promator.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;

    public UserDetailsServiceImpl(@Autowired UserRepository userRepository, @Autowired CredentialRepository credentialRepository) {
        this.userRepository = userRepository;
        this.credentialRepository = credentialRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email);
        var credential = credentialRepository.findCredentialsByUserId(user.getId());
        return new UserAuthDTO(
                user.getEmail(),
                credential.getPassword(),
                user.getRole(),
                !UserStatusDomain.DELETED.equals(user.getStatus()),
                !CredentialStatusDomain.EXPIRED.equals(credential.getStatus()),
                !UserStatusDomain.BLOCKED.equals(user.getStatus()),
                UserStatusDomain.ACTIVE.equals(user.getStatus())
        );
    }

    @Value("${api.security.token.secret}")
    private String secret;

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