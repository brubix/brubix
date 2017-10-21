package com.brubix.brubixservice.controller.inventory.document;

import com.brubix.brubixservice.exception.error.ErrorCode;
import com.brubix.brubixservice.validator.CustomSpringValidatorAdapter;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public class DocumentFormCustomValidator extends CustomSpringValidatorAdapter<DocumentForm> {

    public DocumentFormCustomValidator(SpringValidatorAdapter springValidatorAdapter) {
        super(springValidatorAdapter);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.INVALID_DOCUMENT_FILE_UPLOADS;
    }
}
