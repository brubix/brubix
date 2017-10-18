package com.brubix.brubixservice.service.reference.language;

import com.brubix.brubixservice.controller.reference.language.LanguageForm;
import com.brubix.brubixservice.repository.reference.LanguageMediumRepository;
import com.brubix.entity.reference.Language;

import java.util.List;
import java.util.stream.Collectors;

public class LanguageCommandHandlerImpl implements LanguageCommandHandler {

    private LanguageMediumRepository languageMediumRepository;

    public LanguageCommandHandlerImpl(LanguageMediumRepository languageMediumRepository) {
        this.languageMediumRepository = languageMediumRepository;
    }

    @Override
    public void save(List<LanguageForm.LanguageData> data) {
        languageMediumRepository.save(data
                .stream()
                .map(languageData -> mapToEntity(languageData))
                .collect(Collectors.toList()));

    }

    @Override
    public Language mapToEntity(LanguageForm.LanguageData languageData) {
        Language language = new Language();
        language.setDescription(languageData.getDescription());
        language.setType(languageData.getLanguage());
        return language;
    }
}
