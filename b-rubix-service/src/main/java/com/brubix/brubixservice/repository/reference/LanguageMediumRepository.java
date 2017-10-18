package com.brubix.brubixservice.repository.reference;

import com.brubix.entity.reference.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageMediumRepository extends JpaRepository<Language, Long> {
}
