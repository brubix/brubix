package com.brubix.service.repository.inventory;

import com.brubix.entity.inventory.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
    School findBySchoolCode(String code);

    School findTopByOrderByIdDesc();
}
