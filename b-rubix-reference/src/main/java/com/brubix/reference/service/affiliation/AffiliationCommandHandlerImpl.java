package com.brubix.reference.service.affiliation;

import com.brubix.common.repository.AffiliationRepository;
import com.brubix.entity.reference.Affiliation;
import com.brubix.reference.controller.affiliation.AffiliationRequest;

import java.util.List;
import java.util.stream.Collectors;

public class AffiliationCommandHandlerImpl implements AffiliationCommandHandler {

    private AffiliationRepository repository;

    public AffiliationCommandHandlerImpl(AffiliationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(List<AffiliationRequest.AffiliationData> data) {
        repository.save(data.stream()
                .map(affiliationData -> mapToEntity(affiliationData)).collect(Collectors.toList()));

    }

    @Override
    public Affiliation mapToEntity(AffiliationRequest.AffiliationData affiliationData) {
        Affiliation institutionAffiliation = new Affiliation();
        institutionAffiliation.setAffiliation(affiliationData.getAffiliation());
        institutionAffiliation.setDescription(affiliationData.getDescription());
        return institutionAffiliation;
    }
}
