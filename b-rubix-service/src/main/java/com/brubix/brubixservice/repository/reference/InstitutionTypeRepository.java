package com.brubix.brubixservice.repository.reference;

import com.brubix.entity.reference.Country;
import com.brubix.entity.reference.InstitutionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionTypeRepository extends JpaRepository<InstitutionType, Long>  {
}
