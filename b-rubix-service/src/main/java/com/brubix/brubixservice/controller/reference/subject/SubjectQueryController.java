package com.brubix.brubixservice.controller.reference.subject;

import com.brubix.brubixservice.constant.ApplicationConstant;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.service.reference.subject.SubjectQueryHandler;
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
@RequestMapping(path = "/subjects",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.REFERENCE}, description = StringUtils.SPACE)
public class SubjectQueryController {

    private SubjectQueryHandler subjectQueryHandler;

    public SubjectQueryController(SubjectQueryHandler subjectQueryHandler) {
        this.subjectQueryHandler = subjectQueryHandler;
    }

    @GetMapping(path = "")
    @ApiOperation(
            value = "Get all subjects",
            notes = "Get all subjects",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = INTERNAL_ERROR, response = ErrorResponse.class)
            })
    @ResponseBody
    public ResponseEntity<List<SubjectForm.SubjectData>> findAllSubjects() {
        List<SubjectForm.SubjectData> subjects = subjectQueryHandler.findAllSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }
}
