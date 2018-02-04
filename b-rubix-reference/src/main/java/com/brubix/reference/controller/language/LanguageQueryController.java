package com.brubix.reference.controller.language;

import com.brubix.common.constant.ApplicationConstant;
import com.brubix.common.exception.error.ErrorMessages;
import com.brubix.common.exception.error.ErrorResponse;
import com.brubix.reference.service.language.LanguageQueryHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/languages",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.LANGUAGE_TAG}, description = StringUtils.SPACE)
public class LanguageQueryController {

    private LanguageQueryHandler languageQueryHandler;

    public LanguageQueryController(LanguageQueryHandler languageQueryHandler) {
        this.languageQueryHandler = languageQueryHandler;
    }


    @GetMapping
    @ApiOperation(
            value = "Get reference languages",
            notes = "Get reference languages",
            code = 200,
            response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity<List<LanguageRequest.LanguageData>> findAllLanguages() {
        List<LanguageRequest.LanguageData> languageDataList = languageQueryHandler.findAllLanguages();
        return new ResponseEntity<>(languageDataList, HttpStatus.OK);
    }
}
