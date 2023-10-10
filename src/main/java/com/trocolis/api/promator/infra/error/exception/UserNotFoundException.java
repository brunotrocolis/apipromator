package com.trocolis.api.promator.infra.error.exception;

import com.trocolis.api.promator.infra.error.code.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends UsernameNotFoundException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND.getDescription());
    }
}
