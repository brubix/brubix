package com.brubix.service.service.institution;

import com.brubix.common.exception.error.ErrorCode;
import com.brubix.service.controller.inventory.school.InstitutionCreateRequest;
import com.brubix.service.validator.CustomSpringValidatorAdapter;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public class InstitutionFormCustomValidator extends CustomSpringValidatorAdapter<InstitutionCreateRequest> {

    public InstitutionFormCustomValidator(SpringValidatorAdapter springValidatorAdapter) {
        super(springValidatorAdapter);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.INVALID_DOCUMENT_FILE_UPLOADS;
    }
}
