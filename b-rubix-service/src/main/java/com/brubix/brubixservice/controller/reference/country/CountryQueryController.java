package com.brubix.brubixservice.controller.reference.country;

import com.brubix.brubixservice.constant.ApplicationConstant;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.service.reference.CountryQueryHandler;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.brubix.brubixservice.exception.error.ErrorMessages.*;

@RestController
@RequestMapping(path = "/countries", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.COUNTRY_TAG}, description = StringUtils.SPACE)
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
                    @ApiResponse(code = 400, message = INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public @ResponseBody
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
                    @ApiResponse(code = 400, message = INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = INVALID_COUNTRY_CODE, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity<CountryData> findCountryByCode(@ApiParam(
            name = "ISO3 country code", value = "ISO3 country code",
            required = true) @PathVariable(name = "code") String code) {
        CountryData country = countryQueryHandler.findCountryByCode(code);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

}
