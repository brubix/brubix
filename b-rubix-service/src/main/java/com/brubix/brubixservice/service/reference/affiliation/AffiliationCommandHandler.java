package com.brubix.brubixservice.service.reference.affiliation;

import com.brubix.brubixservice.controller.reference.institutionboard.AffiliationForm;
import com.brubix.entity.reference.InstitutionAffiliation;

import java.util.List;

public interface AffiliationCommandHandler {

    InstitutionAffiliation mapToEntity(AffiliationForm.AffiliationData affiliationData);

    void save(List<AffiliationForm.AffiliationData> data);
}
