package com.brubix.common.repository;

import com.brubix.entity.reference.Affiliation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionAffiliationRepository extends JpaRepository<Affiliation, Long> {

    Affiliation findByAffiliation(String affiliation);
}
