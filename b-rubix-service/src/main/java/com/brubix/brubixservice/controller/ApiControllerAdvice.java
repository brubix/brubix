package com.brubix.brubixservice.controller;

import com.brubix.brubixservice.exception.BrubixException;
import com.brubix.brubixservice.exception.error.ErrorCode;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.exception.validation.FieldErrorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;


@RestControllerAdvice
@Slf4j
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public ApiControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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


    @ExceptionHandler(BrubixException.class)
    public ResponseEntity<ErrorResponse> handleError(BrubixException exception) {
        log.error("Error occurred", exception);
        ErrorCode errorcode = exception.getErrorcode();
        return new ResponseEntity<>(new ErrorResponse(errorcode), errorcode.getHttpStatus());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> anyError(Throwable exception) {
        log.error("Error occurred", exception);
        ErrorCode errorcode = ErrorCode.INTERNAL_ERROR;
        return new ResponseEntity<>(new ErrorResponse(errorcode), errorcode.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        log.error("Form validation failed");
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        List<FieldErrorDetail> fieldErrorDetails = processFieldErrors(fieldErrors);
        ErrorCode errorCode = ErrorCode.INVALID_PAYLOAD;
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        errorResponse.setFieldErrors(fieldErrorDetails);
        return new ResponseEntity<Object>(errorResponse, errorCode.getHttpStatus());
    }

    private List<FieldErrorDetail> processFieldErrors(List<FieldError> fieldErrors) {
        List<FieldErrorDetail> errors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            errors.add(FieldErrorDetail.builder().field(fieldError.getField()).message(localizedErrorMessage).build());
        }
        return errors;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        if (localizedErrorMessage == null) {
            return messageSource.getMessage(fieldError, Locale.ENGLISH);
        }
        return localizedErrorMessage;
    }
}
