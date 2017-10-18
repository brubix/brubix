package com.brubix.brubixservice.service.reference.language;

import com.brubix.brubixservice.controller.reference.institutiontype.InstitutionTypeForm;
import com.brubix.brubixservice.controller.reference.language.LanguageForm;
import com.brubix.entity.reference.Language;

import java.util.List;

public interface LanguageCommandHandler {

    Language mapToEntity(LanguageForm.LanguageData languageData);

    void save(List<LanguageForm.LanguageData> data);
}
