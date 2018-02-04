package com.brubix.reference.service.affiliation;

import com.brubix.reference.controller.affiliation.AffiliationRequest;

import java.util.List;

public interface AffiliationQueryHandler {

    List<AffiliationRequest.AffiliationData> findAllAffiliations();

}
