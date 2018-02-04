package com.brubix.reference.service.affiliation;

        import com.brubix.entity.reference.Affiliation;
        import com.brubix.reference.controller.institutionboard.AffiliationForm;

        import java.util.List;

public interface AffiliationCommandHandler {

    Affiliation mapToEntity(AffiliationForm.AffiliationData affiliationData);

    void save(List<AffiliationForm.AffiliationData> data);
}
