package com.brubix.brubixservice.service.reference.institutiontype;

import com.brubix.brubixservice.controller.reference.institutiontype.InstitutionTypeForm;
import com.brubix.brubixservice.repository.reference.InstitutionTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

public class InstitutionTypeQueryHandlerImpl implements InstitutionTypeQueryHandler {

    private InstitutionTypeRepository repository;

    public InstitutionTypeQueryHandlerImpl(InstitutionTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InstitutionTypeForm.InstitutionTypeData> findAllInstitutionTypes() {
        return repository.findAll()
                .stream()
                .map(institutionType -> {
                    return
                            InstitutionTypeForm.InstitutionTypeData
                                    .builder()
                                    .type(institutionType.getType())
                                    .description(institutionType.getDescription())
                                    .build();

                }).collect(Collectors.toList());
    }
}
