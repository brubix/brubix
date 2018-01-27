package com.brubix.reference.service.institutiontype;

import com.brubix.entity.reference.InstitutionType;
import com.brubix.reference.controller.institutiontype.InstitutionTypeForm;
import com.brubix.reference.repository.InstitutionTypeRepository;

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
