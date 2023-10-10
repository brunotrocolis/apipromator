package com.trocolis.api.promator.service;

import com.trocolis.api.promator.infra.error.code.ErrorCode;
import com.trocolis.api.promator.infra.error.exception.BusinessException;
import com.trocolis.api.promator.infra.error.exception.CredentialNotFoundExcption;
import com.trocolis.api.promator.infra.error.exception.UserNotFoundException;
import com.trocolis.api.promator.model.domain.user.CredentialStatusDomain;
import com.trocolis.api.promator.model.domain.user.UserStatusDomain;
import com.trocolis.api.promator.model.dto.auth.request.ConfirmationRequest;
import com.trocolis.api.promator.model.dto.auth.request.RegisterRequest;
import com.trocolis.api.promator.model.dto.user.UserAuthDTO;
import com.trocolis.api.promator.model.entity.Credential;
import com.trocolis.api.promator.model.entity.User;
import com.trocolis.api.promator.model.repository.CredentialRepository;
import com.trocolis.api.promator.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;

    public UserService(@Autowired UserRepository userRepository,
                       @Autowired CredentialRepository credentialRepository) {
        this.userRepository = userRepository;
        this.credentialRepository = credentialRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.info("Load user by email: {}", email);
        var user = userRepository.findByEmail(email);

        if (user == null) {
            UsernameNotFoundException exception = new UserNotFoundException();
            LOGGER.error(exception.getMessage());
            throw exception;
        }

        var credential = credentialRepository.findCredentialsByUserId(user.getId());
        if (credential == null) {
            UsernameNotFoundException exception = new CredentialNotFoundExcption();
            LOGGER.error(exception.getLocalizedMessage(), exception);
            throw exception;
        }

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

    public UUID geristerUser(RegisterRequest request) throws BusinessException {
        LOGGER.info("Register user: {}", request);

        var user = userRepository.findByEmail(request.email());

        if (user == null) {
            user = new User(request.name(), request.email(), request.birthDate());
        } else {

            LOGGER.warn("E-mail {} already in use", request.email());

            if (UserStatusDomain.DELETED.equals(user.getStatus())) {
                LOGGER.info("User deleted, re-creating user");
                user.setStatus(UserStatusDomain.INACTIVE);
            } else {
                LOGGER.error("{}", user.getStatus());
                LOGGER.error(ErrorCode.EMAIL_ALREADY_IN_USE.getDescription());
                throw new BusinessException(ErrorCode.EMAIL_ALREADY_IN_USE);
            }
        }

        user = userRepository.save(user);

        LOGGER.info("User {} created", user.getId());

        return user.getId();
    }

    public UUID activateUser(ConfirmationRequest request) throws BusinessException {

        LOGGER.info("Activate user: {}", request.userId());

        var user = userRepository.findById(request.userId());

        if (user.isEmpty()) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        user.get().setStatus(UserStatusDomain.ACTIVE);

        var out = userRepository.save(user.get());

        var encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        credentialRepository.save(new Credential(request.userId(), encryptedPassword));

        LOGGER.info("User {} activated", out.getId());

        return out.getId();

    }
}