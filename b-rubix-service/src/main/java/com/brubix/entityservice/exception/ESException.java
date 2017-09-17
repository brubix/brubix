
package com.brubix.entityservice.exception;

import com.brubix.entityservice.exception.error.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@EqualsAndHashCode(callSuper = true)
@Slf4j
public class ESException extends RuntimeException {


    private static final long serialVersionUID = 1L;

    @Getter
    private ErrorCode errorcode;


    public ESException(ErrorCode errorcode) {
        super(errorcode.getMessage());
        this.errorcode = errorcode;
    }


    public static ESException wrap(Throwable ex) {
        log.error("Error:", ex);
        return ex instanceof ESException ? (ESException) ex : new ESException(ErrorCode.INTERNAL_ERROR);
    }
}
