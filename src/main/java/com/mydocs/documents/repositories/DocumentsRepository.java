package com.mydocs.documents.repositories;

import com.mydocs.documents.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentsRepository extends JpaRepository<Document, Integer> {
    List<Document> findByDocumentNumber(int documentNumber);
    List<Document> findByNameStartingWith(String name);
}
