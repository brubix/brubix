package com.brubix.brubixservice.service.reference.language;

import com.brubix.brubixservice.controller.reference.language.LanguageForm;

import java.util.List;

public interface LanguageQueryHandler {
    List<LanguageForm.LanguageData> findAllLanguages();
}
