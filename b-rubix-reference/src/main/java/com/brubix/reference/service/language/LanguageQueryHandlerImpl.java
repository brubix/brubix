package com.brubix.reference.service.language;

import com.brubix.reference.controller.language.LanguageForm;
import com.brubix.reference.repository.LanguageMediumRepository;

import java.util.List;
import java.util.stream.Collectors;

public class LanguageQueryHandlerImpl implements LanguageQueryHandler {

    private final LanguageMediumRepository repository;

    public LanguageQueryHandlerImpl(LanguageMediumRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LanguageForm.LanguageData> findAllLanguages() {
        return repository.findAll()
                .stream()
                .map(language -> {
                    return LanguageForm.LanguageData
                            .builder()
                            .description(language.getDescription())
                            .language(language.getType())
                            .build();
                }).collect(Collectors.toList());
    }
}