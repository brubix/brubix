package com.brubix.brubixservice.controller.reference.school;

import com.brubix.brubixservice.controller.reference.institutionboard.AffiliationForm;
import com.brubix.brubixservice.controller.reference.institutiontype.InstitutionTypeForm;
import com.brubix.brubixservice.controller.reference.language.LanguageForm;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SchoolCreationReferenceData {

    private List<AffiliationForm.AffiliationData> affiliations;
    private List<LanguageForm.LanguageData> languages;
    private List<InstitutionTypeForm.InstitutionTypeData> institutionTypes;
}
