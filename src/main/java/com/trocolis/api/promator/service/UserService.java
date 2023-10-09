package com.trocolis.api.promator.service;

import com.trocolis.api.promator.model.domain.user.CredentialStatusDomain;
import com.trocolis.api.promator.model.domain.user.UserStatusDomain;
import com.trocolis.api.promator.model.dto.auth.request.ConfirmationRequest;
import com.trocolis.api.promator.model.dto.auth.request.RegisterRequest;
import com.trocolis.api.promator.model.dto.user.UserAuthDTO;
import com.trocolis.api.promator.model.entity.Credential;
import com.trocolis.api.promator.model.entity.User;
import com.trocolis.api.promator.model.repository.CredentialRepository;
import com.trocolis.api.promator.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(@Autowired UserRepository userRepository,
                       @Autowired CredentialRepository credentialRepository,
                       @Autowired BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.credentialRepository = credentialRepository;
        this.passwordEncoder = passwordEncoder;
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

    public UUID geristerUser(RegisterRequest request) {
        var user = userRepository.findByEmail(request.email());

        // TODO: Validar de o e-mail já existe na base
        if (user != null) {
            return null;
        }

        user = userRepository.save(new User(request.name(), request.email(), request.birthDate()));
        return user.getId();
    }

    public UUID activateUser(ConfirmationRequest request) {
        var user = userRepository.findById(request.userId());

        // TODO: Validar de o usuário existe
        if (user.isEmpty()) {
            return null;
        }

        user.get().setStatus(UserStatusDomain.ACTIVE);

        var out = userRepository.save(user.get());

        var encryptedPassword = passwordEncoder.encode(request.password());
        credentialRepository.save(new Credential(request.userId(), encryptedPassword));

        return out.getId();

    }
}