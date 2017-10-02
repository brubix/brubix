package com.brubix.brubixservice.validator;

import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.brubixservice.exception.error.ErrorCode;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public class SchoolFormCustomValidator extends CustomSpringValidatorAdapter<SchoolForm> {

    public SchoolFormCustomValidator(SpringValidatorAdapter springValidatorAdapter) {
        super(springValidatorAdapter);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.INVALID_KYC_FILE_UPLOADS;
    }
}
