package com.brubix.brubixservice.controller.inventory.school;

import com.brubix.brubixservice.constant.ApplicationConstant;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.service.inventory.school.SchoolQueryHandler;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.brubix.brubixservice.exception.error.ErrorMessages.*;

@RestController
@RequestMapping(path = {"/schools"}, produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.SCHOOL_TAG}, description = StringUtils.SPACE)
public class SchoolQueryController {

    private SchoolQueryHandler schoolQueryHandler;

    public SchoolQueryController(SchoolQueryHandler schoolQueryHandler) {
        this.schoolQueryHandler = schoolQueryHandler;
    }

    @GetMapping({"/{code}"})
    @ApiOperation(
            value = "Get school by school code",
            notes = "Get school by school code",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = INVALID_SCHOOL_CODE, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity<SchoolData> findSchoolByCode(
            @ApiParam(
                    name = "code", value = "School code",
                    required = true) @PathVariable(value = "code") String code) {
        SchoolData schoolData = schoolQueryHandler.findSchoolByCode(code);
        return new ResponseEntity<>(schoolData, HttpStatus.OK);
    }
}
