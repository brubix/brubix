package com.brubix.brubixservice.service.reference.affiliation;

import com.brubix.brubixservice.controller.reference.institutionboard.AffiliationForm;
import com.brubix.brubixservice.repository.reference.InstitutionAffiliationRepository;
import com.brubix.entity.reference.InstitutionAffiliation;

import java.util.List;
import java.util.stream.Collectors;

public class AffiliationCommandHandlerImpl implements AffiliationCommandHandler {

    private InstitutionAffiliationRepository repository;

    public AffiliationCommandHandlerImpl(InstitutionAffiliationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(List<AffiliationForm.AffiliationData> data) {
        repository.save(data.stream()
                .map(affiliationData -> mapToEntity(affiliationData)).collect(Collectors.toList()));

    }

    @Override
    public InstitutionAffiliation mapToEntity(AffiliationForm.AffiliationData affiliationData) {
        InstitutionAffiliation institutionAffiliation = new InstitutionAffiliation();
        institutionAffiliation.setAffiliation(affiliationData.getAffiliation());
        institutionAffiliation.setDescription(affiliationData.getDescription());
        return institutionAffiliation;
    }
}
