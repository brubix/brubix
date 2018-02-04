package com.brubix.reference.service.language;

import com.brubix.common.repository.LanguageMediumRepository;
import com.brubix.reference.controller.language.LanguageRequest;

import java.util.List;
import java.util.stream.Collectors;

public class LanguageQueryHandlerImpl implements LanguageQueryHandler {

    private final LanguageMediumRepository repository;

    public LanguageQueryHandlerImpl(LanguageMediumRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LanguageRequest.LanguageData> findAllLanguages() {
        return repository.findAll()
                .stream()
                .map(language -> {
                    return LanguageRequest.LanguageData
                            .builder()
                            .description(language.getDescription())
                            .language(language.getType())
                            .build();
                }).collect(Collectors.toList());
    }
}