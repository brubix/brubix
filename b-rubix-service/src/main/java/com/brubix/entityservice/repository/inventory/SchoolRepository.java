package com.brubix.entityservice.repository.inventory;

import com.brubix.model.inventory.School;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sanjaya on 16/09/17.
 */
public interface SchoolRepository extends JpaRepository<School, Long> {
}
