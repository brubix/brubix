package com.brubix.reference.repository;

import com.brubix.entity.reference.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sanjaya on 30/09/17.
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findTopByOrderByIdDesc();

    Subject findByName(String name);
}
