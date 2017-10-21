package com.brubix.brubixservice.service.inventory.document;

import com.brubix.brubixservice.controller.inventory.DocumentData;
import com.brubix.brubixservice.controller.inventory.document.DocumentForm;
import com.brubix.entity.inventory.DocumentInfo;

public interface DocumentCommandHandler {

    void upload(DocumentForm documentForm);

    DocumentInfo mapToEntity(DocumentData documentData);
}
