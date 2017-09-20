package com.brubix.brubixservice.controller.inventory.school;

import com.brubix.brubixservice.constant.ApplicationConstant;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.loader.Loader;
import com.brubix.entity.inventory.School;
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
import java.util.Arrays;

import static com.brubix.brubixservice.exception.error.ErrorMessages.*;

@RestController
@RequestMapping(
        path = {"/inventory"},
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.INVENTORY_TAG}, description = StringUtils.SPACE)
public class SchoolLoaderController {

    private Loader<SchoolForm, School> schoolDataLoader;

    public SchoolLoaderController(Loader<SchoolForm, School> schoolDataLoader) {
        this.schoolDataLoader = schoolDataLoader;
    }

    @PostMapping("/school")
    @ApiOperation(
            value = "Load school",
            notes = "Load school",
            code = 204,
            response = String.class)
    @ApiResponses(
            value = {@ApiResponse(code = 400, message = INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity loadSchool(
            @ApiParam(name = "School",
                    value = "List of schools to be loaded",
                    required = true) @RequestBody @Valid SchoolForm schoolForm) {
        schoolDataLoader.load(Arrays.asList(schoolForm));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
