package com.brubix.reference.controller.institutiontype;

import com.brubix.common.constant.ApplicationConstant;
import com.brubix.common.exception.error.ErrorMessages;
import com.brubix.common.exception.error.ErrorResponse;
import com.brubix.reference.service.institutiontype.InstitutionTypeCommandHandler;
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
@RequestMapping(path = "/institutions",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.REFERENCE}, description = StringUtils.SPACE)
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
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity saveInstitutionTypes(
            @ApiParam(name = "Institution types",
                    value = "List of countries to be saved",
                    required = true) @RequestBody @Valid InstitutionTypeForm form) {
        commandHandler.save(form.getTypes());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
