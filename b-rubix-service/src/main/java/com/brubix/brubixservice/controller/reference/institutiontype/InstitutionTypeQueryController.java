package com.brubix.brubixservice.controller.reference.institutiontype;

import com.brubix.brubixservice.constant.ApplicationConstant;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.service.reference.institutiontype.InstitutionTypeQueryHandler;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.brubix.brubixservice.exception.error.ErrorMessages.*;

@RestController
@RequestMapping(path = "/institution-type",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.INSTITUTION_TYPE}, description = StringUtils.SPACE)
public class InstitutionTypeQueryController {

    private InstitutionTypeQueryHandler queryHandler;

    public InstitutionTypeQueryController(InstitutionTypeQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping
    @ApiOperation(
            value = "List institution types",
            notes = "List institution types",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public @ResponseBody
    ResponseEntity<List<InstitutionTypeForm.InstitutionTypeData>> findAllInstitutionTypes() {
        List<InstitutionTypeForm.InstitutionTypeData> institutionTypes = queryHandler.findAllInstitutionTypes();
        return new ResponseEntity<>(institutionTypes, HttpStatus.OK);
    }

}
