package com.brubix.brubixservice.controller.inventory.school;

import com.brubix.brubixservice.constant.ApplicationConstant;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.service.inventory.school.SchoolCommandHandler;
import com.brubix.brubixservice.service.inventory.school.SchoolCreationResult;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static com.brubix.brubixservice.exception.error.ErrorMessages.*;

@RestController
@RequestMapping(path = {"/schools"})
@Api(tags = {ApplicationConstant.SCHOOL_TAG}, description = StringUtils.SPACE)
public class SchoolCommandController {

    private SchoolCommandHandler schoolDataLoader;

    public SchoolCommandController(SchoolCommandHandler schoolDataLoader) {
        this.schoolDataLoader = schoolDataLoader;
    }

    @PostMapping(path = "",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    @ApiOperation(
            value = "Load school",
            notes = "Load school",
            code = 200, response = SchoolCreationResult.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = INTERNAL_ERROR, response = ErrorResponse.class)
            })
    @ResponseBody
    public ResponseEntity<SchoolCreationResult> createSchool(
            @ApiParam(name = "School",
                    value = "School to be loaded",
                    required = true) @Valid @RequestBody SchoolForm schoolForm,
            @ApiParam(name = "School Logo",
                    value = "School Logo",
                    required = true) @RequestParam(value = "file") MultipartFile schoolLogo) {
        schoolForm.setSchoolLogoFile(schoolLogo);
        List<SchoolCreationResult> schoolCreationResults = schoolDataLoader.load(Arrays.asList(schoolForm));
        return new ResponseEntity<>(schoolCreationResults.stream().findFirst().get(), HttpStatus.OK);
    }

}
