package com.trocolis.api.promator.model.domain.user;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
public enum UserRole {
    ADMIN(List.of(
            new SimpleGrantedAuthority("ROLE_ADMIN"),
            new SimpleGrantedAuthority("ROLE_USER")
    )),
    USER(List.of(
            new SimpleGrantedAuthority("ROLE_USER")
    ));

    private final List<SimpleGrantedAuthority> roles;

    UserRole(List<SimpleGrantedAuthority> roles) {
        this.roles = roles;
    }

}
