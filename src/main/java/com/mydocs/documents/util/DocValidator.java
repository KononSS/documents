package com.mydocs.documents.util;

import com.mydocs.documents.models.Document;
import com.mydocs.documents.services.DocumentsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DocValidator implements Validator {
    private final DocumentsDetailsService documentsDetailsService;

    @Autowired
    public DocValidator(DocumentsDetailsService documentsDetailsService) {
        this.documentsDetailsService = documentsDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Document.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Document document = (Document) o;
        if (documentsDetailsService.docSearch(document.getDocumentNumber()).isPresent())
            errors.rejectValue("documentNumber", "", " Такой документ уже зарегистрирован");
    }
}
