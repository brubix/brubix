package com.brubix.reference.service.affiliation;

import com.brubix.reference.controller.institutionboard.AffiliationForm;
import com.brubix.reference.repository.InstitutionAffiliationRepository;

import java.util.List;
import java.util.stream.Collectors;

public class AffiliationQueryHandlerImpl implements AffiliationQueryHandler {


    private final InstitutionAffiliationRepository affiliationRepository;

    public AffiliationQueryHandlerImpl(InstitutionAffiliationRepository affiliationRepository) {
        this.affiliationRepository = affiliationRepository;
    }

    @Override
    public List<AffiliationForm.AffiliationData> findAllAffiliations() {
        return affiliationRepository.findAll()
                .stream()
                .map(institutionAffiliation -> {

                    return AffiliationForm.AffiliationData
                            .builder()
                            .affiliation(institutionAffiliation.getAffiliation())
                            .description(institutionAffiliation.getDescription())
                            .build();
                }).collect(Collectors.toList());
    }
}
