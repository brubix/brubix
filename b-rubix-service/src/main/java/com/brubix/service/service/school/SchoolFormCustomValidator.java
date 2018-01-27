package com.brubix.service.service.school;

import com.brubix.common.exception.error.ErrorCode;
import com.brubix.service.controller.inventory.school.SchoolForm;
import com.brubix.service.validator.CustomSpringValidatorAdapter;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public class SchoolFormCustomValidator extends CustomSpringValidatorAdapter<SchoolForm> {

    public SchoolFormCustomValidator(SpringValidatorAdapter springValidatorAdapter) {
        super(springValidatorAdapter);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.INVALID_DOCUMENT_FILE_UPLOADS;
    }
}
