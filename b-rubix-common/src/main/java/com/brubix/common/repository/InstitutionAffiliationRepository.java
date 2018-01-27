package com.brubix.common.repository;

import com.brubix.entity.reference.InstitutionAffiliation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionAffiliationRepository extends JpaRepository<InstitutionAffiliation, Long> {

    InstitutionAffiliation findByAffiliation(String affiliation);
}
