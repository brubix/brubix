package com.brubix.reference.service.affiliation;

import com.brubix.reference.controller.institutionboard.AffiliationForm;

import java.util.List;

public interface AffiliationQueryHandler {

    List<AffiliationForm.AffiliationData> findAllAffiliations();

}
