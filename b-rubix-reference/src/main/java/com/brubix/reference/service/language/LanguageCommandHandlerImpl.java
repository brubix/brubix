package com.brubix.reference.service.language;

import com.brubix.common.repository.LanguageMediumRepository;
import com.brubix.entity.reference.Language;
import com.brubix.reference.controller.language.LanguageRequest;

import java.util.List;
import java.util.stream.Collectors;

public class LanguageCommandHandlerImpl implements LanguageCommandHandler {

    private LanguageMediumRepository languageMediumRepository;

    public LanguageCommandHandlerImpl(LanguageMediumRepository languageMediumRepository) {
        this.languageMediumRepository = languageMediumRepository;
    }

    @Override
    public void save(List<LanguageRequest.LanguageData> data) {
        languageMediumRepository.saveAll(data
                .stream()
                .map(languageData -> mapToEntity(languageData))
                .collect(Collectors.toList()));

    }

    @Override
    public Language mapToEntity(LanguageRequest.LanguageData languageData) {
        Language language = new Language();
        language.setDescription(languageData.getDescription());
        language.setType(languageData.getLanguage());
        return language;
    }
}
