package com.brubix.service.controller.inventory.school;

import com.brubix.common.constant.ApplicationConstant;
import com.brubix.common.exception.error.ErrorMessages;
import com.brubix.common.exception.error.ErrorResponse;
import com.brubix.service.service.school.SchoolCode;
import com.brubix.service.service.school.SchoolCommandHandler;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = {"/schools"})
@Api(tags = {ApplicationConstant.SCHOOL_TAG}, description = StringUtils.SPACE)
public class SchoolCommandController {

    private SchoolCommandHandler schoolCommandHandler;

    public SchoolCommandController(SchoolCommandHandler schoolCommandHandler) {
        this.schoolCommandHandler = schoolCommandHandler;
    }

    @PostMapping(path = "",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Create school",
            notes = "Create school",
            code = 200, response = SchoolCode.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    @ResponseBody
    public ResponseEntity<SchoolCode> create(
            @ApiParam(name = "school", value = "School to be created")
            @Valid @RequestBody SchoolForm school) {

        SchoolCode schoolCode = schoolCommandHandler.create(school);
        return new ResponseEntity<>(schoolCode, HttpStatus.OK);
    }


    @PutMapping(path = "/{code}/courses",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Create courses for a school with subjects",
            notes = "Create courses for a school with subjects",
            code = 204, response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity<?> createCoursesOfSchool(
            @ApiParam(name = "code", value = "School code", required = true)
            @PathVariable(value = "code") String code,

            @ApiParam(name = "Courses", value = "Courses with subjects for school", required = true)
            @Valid @RequestBody CourseForm courseForm) {

        courseForm.setSchoolCode(code);
        schoolCommandHandler.create(courseForm);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping(path = "/{code}/faculties",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    @ApiOperation(
            value = "Create faculties for a school",
            notes = "Create faculties for school",
            code = 200, response = SchoolCode.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_DOCUMENT_FILE_UPLOADS, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_FILE, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    @ResponseBody
    public ResponseEntity<?> createTeachersOfSchool(
            @ApiParam(name = "code", value = "School code", required = true)
            @PathVariable(value = "code") String code,

            @ApiParam(name = "faculties", value = "Teachers for a  school", required = true)
            @Valid @RequestBody TeacherForm teacherForm,

            @ApiParam(name = "DocumentInfo", value = "DocumentInfo documents")
            @RequestPart(value = "DocumentInfo", required = false) List<MultipartFile> kycDocuments) {

        teacherForm.setSchoolCode(code);
        //schoolCommandHandler.upload(teacherForm);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
