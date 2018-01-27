

package com.brubix.common.exception.error;

import com.brubix.common.constant.ApplicationConstant;
import com.brubix.common.exception.validation.FieldErrorDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String code;
    private String message;
    private List<FieldErrorDetail> fieldErrors = new ArrayList<>();


    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
    }

    public String getInfo() {
        return String.format("%s/%s", ApplicationConstant.ERROR_INFO_URL, code);
    }

}
