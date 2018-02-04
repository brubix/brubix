package com.brubix.reference.controller.country;

import com.brubix.common.constant.ApplicationConstant;
import com.brubix.common.exception.error.ErrorMessages;
import com.brubix.common.exception.error.ErrorResponse;
import com.brubix.reference.service.country.CountryCommandHandler;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/countries",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.COUNTRY_TAG}, description = StringUtils.SPACE)
public class CountryCommandController {

    private CountryCommandHandler countryCommandHandler;

    public CountryCommandController(CountryCommandHandler countryCommandHandler) {
        this.countryCommandHandler = countryCommandHandler;
    }

    @PostMapping(path = "")
    @ApiOperation(
            value = "Save countries with states,cities",
            notes = "Save countries with states,cities",
            code = 204,
            response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity saveCountries(
            @ApiParam(name = "Countries",
                    value = "List of countries to be saved",
                    required = true) @RequestBody @Valid CountryRequest countryForm) {
        countryCommandHandler.save(countryForm.getCountries());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
