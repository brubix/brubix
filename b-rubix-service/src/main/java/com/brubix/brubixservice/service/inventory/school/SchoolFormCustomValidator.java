package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.document.DocumentForm;
import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.brubixservice.exception.error.ErrorCode;
import com.brubix.brubixservice.validator.CustomSpringValidatorAdapter;
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
