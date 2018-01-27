package com.brubix.reference.service.institutiontype;

import com.brubix.common.repository.InstitutionTypeRepository;
import com.brubix.reference.controller.institutiontype.InstitutionTypeForm;

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
