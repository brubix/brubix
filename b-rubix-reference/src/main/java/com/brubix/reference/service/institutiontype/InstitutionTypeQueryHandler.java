package com.brubix.reference.service.institutiontype;

import com.brubix.reference.controller.institutiontype.InstitutionTypeForm;

import java.util.List;

public interface InstitutionTypeQueryHandler {

    List<InstitutionTypeForm.InstitutionTypeData> findAllInstitutionTypes();
}
