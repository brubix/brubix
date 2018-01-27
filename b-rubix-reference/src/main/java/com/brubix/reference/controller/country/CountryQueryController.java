package com.brubix.reference.controller.country;

import com.brubix.common.constant.ApplicationConstant;
import com.brubix.common.exception.error.ErrorMessages;
import com.brubix.common.exception.error.ErrorResponse;
import com.brubix.reference.service.country.CountryQueryHandler;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/countries", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.REFERENCE}, description = StringUtils.SPACE)
public class CountryQueryController {

    private CountryQueryHandler countryQueryHandler;

    public CountryQueryController(CountryQueryHandler countryQueryHandler) {
        this.countryQueryHandler = countryQueryHandler;
    }

    @GetMapping
    @ApiOperation(
            value = "List countries with states",
            notes = "List countries with states",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public
    @ResponseBody
    ResponseEntity<List<CountryData>> findAllCountries() {
        List<CountryData> countries = countryQueryHandler.findAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping({"/{code}"})
    @ApiOperation(
            value = "Get country by code",
            notes = "Get country by code",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_COUNTRY_CODE, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity<CountryData> findCountryByCode(
            @ApiParam(
                    name = "code", value = "ISO3 country code",
                    required = true) @PathVariable(value = "code") String code) {
        CountryData country = countryQueryHandler.findCountryByCode(code);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

}
