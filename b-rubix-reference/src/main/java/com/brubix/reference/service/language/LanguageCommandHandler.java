package com.brubix.reference.service.language;

import com.brubix.entity.reference.Language;
import com.brubix.reference.controller.language.LanguageForm;

import java.util.List;

public interface LanguageCommandHandler {

    Language mapToEntity(LanguageForm.LanguageData languageData);

    void save(List<LanguageForm.LanguageData> data);
}
