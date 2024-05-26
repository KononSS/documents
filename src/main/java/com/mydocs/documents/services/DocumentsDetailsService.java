package com.mydocs.documents.services;

import com.mydocs.documents.models.Document;
import com.mydocs.documents.repositories.DocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentsDetailsService {
    private final DocumentsRepository documentsRepository;

    @Autowired
    public DocumentsDetailsService(DocumentsRepository documentsRepository) {
        this.documentsRepository = documentsRepository;
    }

    public Optional<Document> docSearch(int number){
        return documentsRepository.findByDocumentNumber(number).stream().findAny();
    }

    public List<Document> findAll(boolean sortByNumber) {
        if (sortByNumber)
            return documentsRepository.findAll(Sort.by("documentNumber"));
        else
            return documentsRepository.findAll();
    }
    public List<Document> searchByName(String name) {
        return documentsRepository.findByNameStartingWith(name);
    }

    @Transactional
    public void registerDocument(Document document){
        documentsRepository.save(document);
    }

}
