package com.brubix.reference.service.language;

import com.brubix.reference.controller.language.LanguageForm;

import java.util.List;

public interface LanguageQueryHandler {
    List<LanguageForm.LanguageData> findAllLanguages();
}
