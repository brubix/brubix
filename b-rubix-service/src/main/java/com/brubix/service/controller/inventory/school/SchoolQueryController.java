package com.brubix.service.controller.inventory.school;

import com.brubix.common.constant.ApplicationConstant;
import com.brubix.common.exception.error.ErrorMessages;
import com.brubix.common.exception.error.ErrorResponse;
import com.brubix.service.service.school.SchoolQueryHandler;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_SCHOOL_CODE, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity<SchoolQueryData> findSchoolByCode(
            @ApiParam(
                    name = "code", value = "School code",
                    required = true) @PathVariable(value = "code") String code) {
        SchoolQueryData schoolData = schoolQueryHandler.findSchoolByCode(code);
        return new ResponseEntity<>(schoolData, HttpStatus.OK);
    }


    @GetMapping({"/{code}/courses"})
    @ApiOperation(
            value = "Get all courses for a school",
            notes = "Get all courses for a school",
            httpMethod = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_SCHOOL_CODE, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity<List<CourseForm.CourseData>> findAllCoursesBySchoolCode(
            @ApiParam(name = "code", value = "School code", required = true)
            @PathVariable(value = "code") String schoolCode
    ) {
        List<CourseForm.CourseData> courses = schoolQueryHandler.findAllCoursesBySchoolCode(schoolCode);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
