package com.brubix.brubixservice.service.reference.institutiontype;

import com.brubix.brubixservice.controller.reference.institutiontype.InstitutionTypeForm;
import com.brubix.brubixservice.repository.reference.InstitutionTypeRepository;
import com.brubix.entity.reference.InstitutionType;

import java.util.List;
import java.util.stream.Collectors;

public class InstitutionTypeCommandHandlerImpl implements InstitutionTypeCommandHandler {

    private InstitutionTypeRepository repository;

    public InstitutionTypeCommandHandlerImpl(InstitutionTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public InstitutionType mapToEntity(InstitutionTypeForm.InstitutionTypeData institutionType) {
        return null;
    }

    @Override
    public void save(List<InstitutionTypeForm.InstitutionTypeData> data) {
        List<InstitutionType> institutionTypes = data.stream()
                .map(institutionTypeData -> {
                    InstitutionType type = new InstitutionType();
                    type.setType(institutionTypeData.getType());
                    type.setDescription(institutionTypeData.getDescription());
                    return type;
                }).collect(Collectors.toList());
        repository.save(institutionTypes);
    }
}
