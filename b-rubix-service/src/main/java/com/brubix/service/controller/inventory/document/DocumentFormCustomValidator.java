package com.brubix.service.controller.inventory.document;

import com.brubix.common.exception.error.ErrorCode;
import com.brubix.service.validator.CustomSpringValidatorAdapter;
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
