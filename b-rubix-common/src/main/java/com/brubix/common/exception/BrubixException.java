
package com.brubix.common.exception;

import com.brubix.common.exception.error.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@EqualsAndHashCode(callSuper = true)
@Slf4j
public class BrubixException extends RuntimeException {


    private static final long serialVersionUID = 1L;

    @Getter
    private ErrorCode errorcode;


    public BrubixException(ErrorCode errorcode) {
        super(errorcode.getMessage());
        this.errorcode = errorcode;
    }


    public static BrubixException wrap(Throwable ex) {
        log.error("Error:", ex);
        return ex instanceof BrubixException ? (BrubixException) ex : new BrubixException(ErrorCode.INTERNAL_ERROR);
    }
}
