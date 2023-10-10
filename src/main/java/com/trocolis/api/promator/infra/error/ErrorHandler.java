package com.trocolis.api.promator.infra.error;

import com.trocolis.api.promator.infra.error.code.ErrorCode;
import com.trocolis.api.promator.infra.error.exception.BusinessException;
import com.trocolis.api.promator.infra.error.exception.CredentialNotFoundExcption;
import com.trocolis.api.promator.infra.error.exception.UserNotFoundException;
import com.trocolis.api.promator.model.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(new ErrorResponse(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        return handleBusinessException(new BusinessException(ErrorCode.USER_NOT_FOUND, exception));
    }

    @ExceptionHandler(CredentialNotFoundExcption.class)
    public ResponseEntity<ErrorResponse> handleEmailAalreadyInUseException(CredentialNotFoundExcption exception) {
        return handleBusinessException(new BusinessException(ErrorCode.CREDENTIAL_NOT_FOUND, exception));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException exception) {
        return handleBusinessException(new BusinessException(ErrorCode.BAD_CREDENTIALS, exception));
    }
}
