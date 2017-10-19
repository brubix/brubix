package com.brubix.brubixservice.controller.reference.subject;

import com.brubix.brubixservice.constant.ApplicationConstant;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.service.reference.subject.SubjectCommandHandler;
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
@RequestMapping(path = "/subjects",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.REFERENCE}, description = StringUtils.SPACE)
public class SubjectCommandController {

    private SubjectCommandHandler subjectCommandHandler;

    public SubjectCommandController(SubjectCommandHandler subjectCommandHandler) {
        this.subjectCommandHandler = subjectCommandHandler;
    }


    @PostMapping(path = "")
    @ApiOperation(
            value = "Create subjects",
            notes = "Create subjects",
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
    public ResponseEntity saveSubjects(
            @ApiParam(name = "Subjects",
                    value = "List of subjects to be created",
                    required = true) @RequestBody @Valid SubjectForm subjectForm) {
        subjectCommandHandler.save(subjectForm.getSubjects());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
