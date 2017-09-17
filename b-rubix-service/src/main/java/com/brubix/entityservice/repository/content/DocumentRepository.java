package com.brubix.entityservice.repository.content;

import com.brubix.model.content.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
