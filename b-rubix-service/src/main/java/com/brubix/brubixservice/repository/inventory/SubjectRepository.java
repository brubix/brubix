package com.brubix.brubixservice.repository.inventory;

import com.brubix.entity.inventory.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sanjaya on 30/09/17.
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findTopByOrderByIdDesc();
}
