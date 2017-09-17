

package com.brubix.entityservice.exception.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static com.brubix.entityservice.constant.EntityServiceConstant.ERROR_INFO_URL;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "timestamp", ignoreUnknown = true)
public class ErrorResponse {

    private String code;
    private String message;
    private Long timestamp;

    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
        this.timestamp = Instant.now()
                .toEpochMilli();
    }

    public String getInfo() {
        return String.format("%s/%s", ERROR_INFO_URL, code);
    }

}
