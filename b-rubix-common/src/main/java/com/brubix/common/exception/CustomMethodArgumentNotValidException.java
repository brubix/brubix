package com.brubix.common.exception;

import com.brubix.common.exception.error.ErrorCode;
import lombok.Getter;
import org.springframework.validation.BindingResult;

public class CustomMethodArgumentNotValidException extends RuntimeException {

    @Getter
    private BindingResult bindingResult;

    @Getter
    private ErrorCode errorcode;


    public CustomMethodArgumentNotValidException(BindingResult bindingResult, ErrorCode errorcode) {
        this.bindingResult = bindingResult;
        this.errorcode = errorcode;
    }
}
