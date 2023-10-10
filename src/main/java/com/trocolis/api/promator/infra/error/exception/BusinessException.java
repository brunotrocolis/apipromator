package com.trocolis.api.promator.infra.error.exception;

import com.trocolis.api.promator.infra.error.code.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends Exception {

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getDescription();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getDescription(), cause);
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getDescription();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public BusinessException(int code, String message, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}