package com.brubix.service.service.document;

import com.brubix.service.controller.inventory.DocumentData;
import com.brubix.service.controller.inventory.document.DocumentForm;
import com.brubix.entity.inventory.DocumentInfo;

public interface DocumentCommandHandler {

    void upload(DocumentForm documentForm);

    DocumentInfo mapToEntity(DocumentData documentData);
}
