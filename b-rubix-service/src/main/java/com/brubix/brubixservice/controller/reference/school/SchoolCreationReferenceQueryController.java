package com.brubix.brubixservice.controller.reference.school;

import com.brubix.brubixservice.constant.ApplicationConstant;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.service.reference.affiliation.AffiliationQueryHandler;
import com.brubix.brubixservice.service.reference.institutiontype.InstitutionTypeQueryHandler;
import com.brubix.brubixservice.service.reference.language.LanguageQueryHandler;
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

import static com.brubix.brubixservice.exception.error.ErrorMessages.*;

@RestController
@RequestMapping(path = "/reference/schools",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {ApplicationConstant.REFERENCE}, description = StringUtils.SPACE)
public class SchoolCreationReferenceQueryController {

    private final LanguageQueryHandler languageQueryHandler;
    private final InstitutionTypeQueryHandler institutionTypeQueryHandler;
    private final AffiliationQueryHandler affiliationQueryHandler;


    public SchoolCreationReferenceQueryController(LanguageQueryHandler languageQueryHandler,
                                                  InstitutionTypeQueryHandler institutionTypeQueryHandler,
                                                  AffiliationQueryHandler affiliationQueryHandler) {
        this.languageQueryHandler = languageQueryHandler;
        this.institutionTypeQueryHandler = institutionTypeQueryHandler;
        this.affiliationQueryHandler = affiliationQueryHandler;
    }


    @GetMapping(path = "")
    @ApiOperation(
            value = "Returns all reference data required for to upload a school",
            notes = "Returns all reference data required for to upload a school",
            code = 200,
            response = SchoolCreationReferenceData.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = INVALID_HEADER, response = ErrorResponse.class),
                    @ApiResponse(code = 400, message = INVALID_PAYLOAD, response = ErrorResponse.class),
                    @ApiResponse(code = 404, message = UNSUPPORTED_API, response = ErrorResponse.class),
                    @ApiResponse(code = 405, message = INVALID_METHOD, response = ErrorResponse.class),
                    @ApiResponse(code = 500, message = INTERNAL_ERROR, response = ErrorResponse.class)
            })
    public ResponseEntity<SchoolCreationReferenceData> retrieveSchoolCreationReferenceData() {

        SchoolCreationReferenceData schoolCreationReferenceData = SchoolCreationReferenceData.builder()
                .affiliations(affiliationQueryHandler.findAllAffiliations())
                .languages(languageQueryHandler.findAllLanguages())
                .institutionTypes(institutionTypeQueryHandler.findAllInstitutionTypes())
                .build();
        return new ResponseEntity<>(schoolCreationReferenceData, HttpStatus.OK);
    }
}
