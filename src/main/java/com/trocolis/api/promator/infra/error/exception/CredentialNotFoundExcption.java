package com.trocolis.api.promator.infra.error.exception;

import com.trocolis.api.promator.infra.error.code.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CredentialNotFoundExcption extends UsernameNotFoundException {

    public CredentialNotFoundExcption() {
        super(ErrorCode.CREDENTIAL_NOT_FOUND.getDescription());
    }

}
