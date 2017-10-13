package com.brubix.brubixservice.controller.reference.institutiontype;

import com.brubix.brubixservice.constant.ApplicationConstant;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.service.reference.institutiontype.InstitutionTypeCommandHandler;
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

import static com.brubix.brubixservice.exception.error.ErrorMessages.*;

@RestController
@RequestMapping(path = "/institution-type",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.INSTITUTION_TYPE}, description = StringUtils.SPACE)
public class InstitutionTypeCommandController {


    private InstitutionTypeCommandHandler commandHandler;

    public InstitutionTypeCommandController(InstitutionTypeCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping(path = "")
    @ApiOperation(
            value = "Save institution types",
            notes = "Save institution types",
            code = 204,
            response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity saveInstitutionTypes(
            @ApiParam(name = "Countries",
                    value = "List of countries to be saved",
                    required = true) @RequestBody @Valid InstitutionTypeForm form) {
        commandHandler.save(form.getTypes());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
