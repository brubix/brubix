package com.brubix.reference.service.language;

import com.brubix.reference.controller.language.LanguageRequest;

import java.util.List;

public interface LanguageQueryHandler {
    List<LanguageRequest.LanguageData> findAllLanguages();
}
