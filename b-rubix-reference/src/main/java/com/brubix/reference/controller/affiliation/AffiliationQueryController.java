package com.brubix.reference.controller.affiliation;

import com.brubix.common.constant.ApplicationConstant;
import com.brubix.common.exception.error.ErrorMessages;
import com.brubix.common.exception.error.ErrorResponse;
import com.brubix.reference.service.affiliation.AffiliationQueryHandler;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Sanjaya on 04/02/18.
 */
@RestController
@RequestMapping(path = "/affiliations", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.AFFILIATION_TAG}, description = StringUtils.SPACE)
public class AffiliationQueryController {

    private AffiliationQueryHandler affiliationQueryHandler;

    public AffiliationQueryController(AffiliationQueryHandler affiliationQueryHandler) {
        this.affiliationQueryHandler = affiliationQueryHandler;
    }


    @GetMapping
    @ApiOperation(
            value = "Get all available affiliations",
            notes = "Get all available affiliations",
            code = 200,
            response = String.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = ErrorMessages.INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = ErrorMessages.UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = ErrorMessages.INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = ErrorMessages.INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity<List<AffiliationRequest.AffiliationData>> getAllAffiliation() {
        List<AffiliationRequest.AffiliationData> affiliationDataList = affiliationQueryHandler.findAllAffiliations();
        return new ResponseEntity<>(affiliationDataList, HttpStatus.OK);
    }
}
