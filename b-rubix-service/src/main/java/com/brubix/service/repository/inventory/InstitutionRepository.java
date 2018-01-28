package com.brubix.service.repository.inventory;

import com.brubix.entity.inventory.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    Institution findByInstitutionCode(String code);

    Institution findTopByOrderByIdDesc();

    Institution findByInstitutionName(String name);

}
