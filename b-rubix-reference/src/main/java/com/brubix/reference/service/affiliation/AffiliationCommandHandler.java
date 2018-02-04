package com.brubix.reference.service.affiliation;

        import com.brubix.entity.reference.Affiliation;
        import com.brubix.reference.controller.affiliation.AffiliationRequest;

        import java.util.List;

public interface AffiliationCommandHandler {

    Affiliation mapToEntity(AffiliationRequest.AffiliationData affiliationData);

    void save(List<AffiliationRequest.AffiliationData> data);
}
