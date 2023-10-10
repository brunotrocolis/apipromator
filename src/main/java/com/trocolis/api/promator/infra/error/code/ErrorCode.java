package com.trocolis.api.promator.infra.error.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND(1, HttpStatus.NOT_FOUND, "User not found"),
    USER_ALREADY_EXISTS(2, HttpStatus.CONFLICT, "User already exists"),
    USER_BLOCKED(3, HttpStatus.FORBIDDEN, "User blocked"),
    USER_NOT_ACTIVATED(4, HttpStatus.FORBIDDEN, "User not activated"),
    USER_NOT_VERIFIED(5, HttpStatus.FORBIDDEN, "User not verified"),
    USER_NOT_CONFIRMED(6, HttpStatus.FORBIDDEN, "User not confirmed"),
    USER_NOT_AUTHENTICATED(7, HttpStatus.FORBIDDEN, "User not authenticated"),
    USER_BANNED(8, HttpStatus.FORBIDDEN, "User banned"),
    EMAIL_NOT_FOUND(9, HttpStatus.NOT_FOUND, "Email not found"),
    CREDENTIAL_NOT_FOUND(10, HttpStatus.NOT_FOUND, "Credential not found"),
    EMAIL_ALREADY_IN_USE(11, HttpStatus.CONFLICT, "Email already in use"),
    ERROR_GENERATING_TOKEN(12, HttpStatus.INTERNAL_SERVER_ERROR, "Error generating token"),
    BAD_CREDENTIALS(13, HttpStatus.UNAUTHORIZED, "Bad credentials"),

    // ...
    UNDEFINED(999, HttpStatus.INTERNAL_SERVER_ERROR, "Undefined");

    private final int errorCode;
    private final String description;
    private final HttpStatus httpStatus;

    ErrorCode(int errorCode, HttpStatus httpStatus, String description) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.description = description;
    }

    public static ErrorCode get(int errorCode) {
        for (ErrorCode errorCode1 : ErrorCode.values()) {
            if (errorCode1.getErrorCode() == errorCode) {
                return errorCode1;
            }
        }
        return UNDEFINED;
    }

    @Override
    public String toString() {
        return this.errorCode + " - " + this.description;
    }
}
