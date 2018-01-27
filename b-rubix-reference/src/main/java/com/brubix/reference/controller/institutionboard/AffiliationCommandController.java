package com.brubix.reference.controller.institutionboard;

import com.brubix.common.constant.ApplicationConstant;
import com.brubix.common.exception.error.ErrorMessages;
import com.brubix.common.exception.error.ErrorResponse;
import com.brubix.reference.service.affiliation.AffiliationCommandHandler;
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
@RequestMapping(path = "/affiliations",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.REFERENCE}, description = StringUtils.SPACE)
public class AffiliationCommandController {

    private AffiliationCommandHandler affiliationCommandHandler;

    public AffiliationCommandController(AffiliationCommandHandler affiliationCommandHandler) {
        this.affiliationCommandHandler = affiliationCommandHandler;
    }


    @PostMapping(path = "")
    @ApiOperation(
            value = "Save institution affiliations",
            notes = "Save institution affiliations",
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
    public ResponseEntity saveBoards(
            @ApiParam(name = "Affiliation types",
                    value = "Institution affiliations to be saved",
                    required = true) @RequestBody @Valid AffiliationForm form) {
        affiliationCommandHandler.save(form.getBoards());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
