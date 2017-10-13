package com.brubix.brubixservice.service.reference.institutiontype;

import com.brubix.brubixservice.controller.reference.institutiontype.InstitutionTypeForm;
import com.brubix.entity.reference.InstitutionType;

import java.util.List;

public interface InstitutionTypeCommandHandler {

    InstitutionType mapToEntity(InstitutionTypeForm.InstitutionTypeData institutionType);

    void save(List<InstitutionTypeForm.InstitutionTypeData> data);
}
