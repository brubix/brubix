package com.brubix.entityservice.controller;

import com.brubix.entityservice.exception.error.ErrorResponse;
import com.brubix.entityservice.service.DummyService;
import com.brubix.model.Dummy;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.brubix.entityservice.exception.error.ErrorMessages.FAIL_AUTH;
import static com.brubix.entityservice.exception.error.ErrorMessages.INVALID_HEADER;
import static com.brubix.entityservice.exception.error.ErrorMessages.INVALID_METHOD;
import static com.brubix.entityservice.exception.error.ErrorMessages.INVALID_PAYLOAD;
import static com.brubix.entityservice.exception.error.ErrorMessages.UNSUPPORTED_API;

@RestController
@RequestMapping("/entity")
public class DummyController {

    @Autowired
    private DummyService dummyService;

    @PostMapping("")
    @ApiOperation(
            value = "Save dummy value",
            notes = "Save dummy value end point.",
            code = 200,
            response = String.class)
    @ApiResponses(
            value = {@ApiResponse(code = 400, message = INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 401, message = FAIL_AUTH, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
            })

    public ResponseEntity saveDummyValue(
            @ApiParam(name = "Dummy", value = "Dummy values", required = true)
            @RequestBody(required = true) Dummy data) {
        Dummy value = dummyService.create(data.getData());
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}
