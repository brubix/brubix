package com.brubix.reference.service.affiliation;

import com.brubix.common.repository.AffiliationRepository;
import com.brubix.reference.controller.affiliation.AffiliationRequest;

import java.util.List;
import java.util.stream.Collectors;

public class AffiliationQueryHandlerImpl implements AffiliationQueryHandler {


    private final AffiliationRepository affiliationRepository;

    public AffiliationQueryHandlerImpl(AffiliationRepository affiliationRepository) {
        this.affiliationRepository = affiliationRepository;
    }

    @Override
    public List<AffiliationRequest.AffiliationData> findAllAffiliations() {
        return affiliationRepository.findAll()
                .stream()
                .map(institutionAffiliation -> {

                    return AffiliationRequest.AffiliationData
                            .builder()
                            .affiliation(institutionAffiliation.getAffiliation())
                            .description(institutionAffiliation.getDescription())
                            .build();
                }).collect(Collectors.toList());
    }
}
