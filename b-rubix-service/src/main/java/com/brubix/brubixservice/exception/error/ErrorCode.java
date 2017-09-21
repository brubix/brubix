package com.brubix.brubixservice.exception.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;


public enum ErrorCode {

    //400
    INVALID_PAYLOAD("1", ErrorMessages.INVALID_PAYLOAD, HttpStatus.BAD_REQUEST),
    INVALID_HEADER("2", ErrorMessages.INVALID_HEADER, HttpStatus.BAD_REQUEST),
    LOADING_ERROR("3", ErrorMessages.LOADING_ERROR, HttpStatus.BAD_REQUEST),
    INVALID_FILE("5", ErrorMessages.INVALID_FILE, HttpStatus.BAD_REQUEST),

    //500
    INTERNAL_ERROR("4", ErrorMessages.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);


    @Getter
    private String code;

    @Getter
    private String message;

    @Getter
    private HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return String.format("%s.%s", httpStatus.value(), code);
    }
}
