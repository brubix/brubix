

package com.brubix.brubixservice.exception.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.brubix.brubixservice.constant.ApplicationConstant.ERROR_INFO_URL;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "timestamp", ignoreUnknown = true)
public class ErrorResponse {

    private String code;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
    }

    public String getInfo() {
        return String.format("%s/%s", ERROR_INFO_URL, code);
    }

}
