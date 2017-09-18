package com.brubix.brubixservice.repository.content;

import com.brubix.entity.content.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
