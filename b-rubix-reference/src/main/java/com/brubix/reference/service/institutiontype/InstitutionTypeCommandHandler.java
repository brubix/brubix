package com.brubix.reference.service.institutiontype;

import com.brubix.entity.reference.InstitutionType;
import com.brubix.reference.controller.institutiontype.InstitutionTypeForm;

import java.util.List;

public interface InstitutionTypeCommandHandler {

    InstitutionType mapToEntity(InstitutionTypeForm.InstitutionTypeData institutionType);

    void save(List<InstitutionTypeForm.InstitutionTypeData> data);
}
