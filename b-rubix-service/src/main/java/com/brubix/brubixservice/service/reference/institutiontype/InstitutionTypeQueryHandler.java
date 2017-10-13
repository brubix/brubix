package com.brubix.brubixservice.service.reference.institutiontype;

import com.brubix.brubixservice.controller.reference.institutiontype.InstitutionTypeForm;

import java.util.List;

public interface InstitutionTypeQueryHandler {

    List<InstitutionTypeForm.InstitutionTypeData> findAllInstitutionTypes();
}
