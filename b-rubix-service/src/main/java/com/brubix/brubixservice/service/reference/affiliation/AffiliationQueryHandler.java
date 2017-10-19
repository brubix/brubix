package com.brubix.brubixservice.service.reference.affiliation;

import com.brubix.brubixservice.controller.reference.institutionboard.AffiliationForm;

import java.util.List;

public interface AffiliationQueryHandler {

    List<AffiliationForm.AffiliationData> findAllAffiliations();

}
