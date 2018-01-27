package com.brubix.common.repository;

import com.brubix.entity.reference.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageMediumRepository extends JpaRepository<Language, Long> {
    Language findByType(String type);
}
