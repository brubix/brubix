package com.brubix.entityservice.controller;

import com.brubix.entityservice.exception.error.ErrorCode;
import com.brubix.entityservice.exception.error.ErrorResponse;
import com.brubix.entityservice.exception.ESException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Set;


@RestControllerAdvice
@Slf4j
public class OrderApiControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
        if (!CollectionUtils.isEmpty(supportedMethods)) {
            headers.setAllow(supportedMethods);
        }
        return getErrorResponse(ErrorCode.INVALID_PAYLOAD, headers, status);
    }


    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getErrorResponse(ErrorCode.INVALID_PAYLOAD, headers, status);
    }

    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getErrorResponse(ErrorCode.INVALID_HEADER, headers, status);
    }

    private ResponseEntity<Object> getErrorResponse(ErrorCode errorCode, HttpHeaders headers, HttpStatus status) {
        return new ResponseEntity<Object>(new ErrorResponse(errorCode), headers, status);
    }


    @ExceptionHandler(ESException.class)
    public ResponseEntity<ErrorResponse> handleError(ESException exception) {
        log.error("Error occurred", exception);
        ErrorCode errorcode = exception.getErrorcode();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(errorcode), errorcode.getHttpStatus());
    }
}
