package com.brubix.reference.service.affiliation;

        import com.brubix.entity.reference.InstitutionAffiliation;
        import com.brubix.reference.controller.institutionboard.AffiliationForm;

        import java.util.List;

public interface AffiliationCommandHandler {

    InstitutionAffiliation mapToEntity(AffiliationForm.AffiliationData affiliationData);

    void save(List<AffiliationForm.AffiliationData> data);
}
