package com.brubix.reference.service.language;

import com.brubix.entity.reference.Language;
import com.brubix.reference.controller.language.LanguageRequest;

import java.util.List;

public interface LanguageCommandHandler {

    Language mapToEntity(LanguageRequest.LanguageData languageData);

    void save(List<LanguageRequest.LanguageData> data);
}
