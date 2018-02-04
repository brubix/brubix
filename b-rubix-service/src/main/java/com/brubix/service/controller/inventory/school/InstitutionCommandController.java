package com.brubix.service.controller.inventory.school;

import com.brubix.common.constant.ApplicationConstant;
import com.brubix.common.exception.error.ErrorMessages;
import com.brubix.common.exception.error.ErrorResponse;
import com.brubix.service.service.institution.InstitutionCode;
import com.brubix.service.service.institution.InstitutionCommandHandler;
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
@Api(tags = {ApplicationConstant.INSTITUTION_TAG}, description = StringUtils.SPACE)
public class InstitutionCommandController {

    private InstitutionCommandHandler institutionCommandHandler;

    public InstitutionCommandController(InstitutionCommandHandler schoolCommandHandler) {
        this.institutionCommandHandler = schoolCommandHandler;
    }

    @PostMapping(path = "",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Create institution",
            notes = "Create institution",
            code = 200, response = InstitutionCode.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    @ResponseBody
    public ResponseEntity<InstitutionCode> create(
            @ApiParam(name = "institution", value = "Institution to be created")
            @Valid @RequestBody InstitutionCreateRequest institutionCreateRequest) {

        InstitutionCode institutionCode = institutionCommandHandler.create(institutionCreateRequest);
        return new ResponseEntity<>(institutionCode, HttpStatus.OK);
    }


    @PutMapping(path = "/{code}/courses",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Create courses for a institution with subjects",
            notes = "Create courses for a institution with subjects",
            code = 204, response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity<?> createCoursesOfSchool(
            @ApiParam(name = "code", value = "Institution code", required = true)
            @PathVariable(value = "code") String code,

            @ApiParam(name = "Courses", value = "Courses with subjects for institution", required = true)
            @Valid @RequestBody CourseForm courseForm) {

        courseForm.setSchoolCode(code);
        institutionCommandHandler.create(courseForm);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping(path = "/{code}/faculties",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    @ApiOperation(
            value = "Create faculties for a institution",
            notes = "Create faculties for institution",
            code = 200, response = InstitutionCode.class)
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
            @ApiParam(name = "code", value = "Institution code", required = true)
            @PathVariable(value = "code") String code,

            @ApiParam(name = "faculties", value = "Teachers for a  institution", required = true)
            @Valid @RequestBody TeacherForm teacherForm,

            @ApiParam(name = "DocumentInfo", value = "DocumentInfo documents")
            @RequestPart(value = "DocumentInfo", required = false) List<MultipartFile> kycDocuments) {

        teacherForm.setSchoolCode(code);
        //institutionCommandHandler.upload(teacherForm);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
