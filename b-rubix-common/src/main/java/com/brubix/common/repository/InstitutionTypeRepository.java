package com.brubix.common.repository;

import com.brubix.entity.reference.InstitutionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionTypeRepository extends JpaRepository<InstitutionType, Long>  {
    InstitutionType findByType(String type);
}
