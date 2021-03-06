package com.brubix.common.exception.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;


public enum ErrorCode {

    //400
    INVALID_PAYLOAD("1", ErrorMessages.INVALID_PAYLOAD, HttpStatus.BAD_REQUEST),
    INVALID_HEADER("2", ErrorMessages.INVALID_HEADER, HttpStatus.BAD_REQUEST),
    INVALID_FILE("5", ErrorMessages.INVALID_FILE, HttpStatus.BAD_REQUEST),
    INVALID_COUNTRY_CODE("6", ErrorMessages.INVALID_COUNTRY_CODE, HttpStatus.BAD_REQUEST),
    INVALID_DOCUMENT_FILE_UPLOADS("7", ErrorMessages.INVALID_DOCUMENT_FILE_UPLOADS, HttpStatus.BAD_REQUEST),
    INVALID_INSTITUTION_CODE("8", ErrorMessages.INVALID_INSTITUTION_CODE, HttpStatus.BAD_REQUEST),
    INVALID_SUBJECT_NAME("9", ErrorMessages.INVALID_SUBJECT_NAME, HttpStatus.BAD_REQUEST),
    ALREADY_INSTITUTION_REGISTERED("10", ErrorMessages.ALREADY_INSTITUTION_REGISTERED, HttpStatus.BAD_REQUEST),
    ALREADY_USER_REGISTERED("11", ErrorMessages.ALREADY_USER_REGISTERED, HttpStatus.BAD_REQUEST),
    ALREADY_PRESENT_FACEBOOK("12", ErrorMessages.ALREADY_PRESENT_FACEBOOK, HttpStatus.BAD_REQUEST),
    ALREADY_PRESENT_TWITTER("13", ErrorMessages.ALREADY_PRESENT_TWITTER, HttpStatus.BAD_REQUEST),
    ALREADY_PRESENT_GPLUS("14", ErrorMessages.ALREADY_PRESENT_GPLUS, HttpStatus.BAD_REQUEST),
    ALREADY_PRESENT_LINKEDIN("15", ErrorMessages.ALREADY_PRESENT_LINKEDIN, HttpStatus.BAD_REQUEST),

    //500
    INTERNAL_ERROR("4", ErrorMessages.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
    LOADING_ERROR("3", ErrorMessages.LOADING_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);


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
