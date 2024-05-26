package com.mydocs.documents.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "номер_документа")
    @Min(value = 1, message = "Номер должен быть больше нуля")
    @Max(value = 10000, message = "Номер не должен быть больше 10000")
    private int documentNumber;
    @Column(name = "название")
    @Size(min = 1, max = 50, message = "Название не должно быть пустым и размером не более 50 символов")
    private String name;
    @Column(name = "дата_создания")
    @Pattern(regexp = "^\\d{4}-[01]\\d-[0-3]\\d$", message = "дата должна быть в формате ГГ-ММ-ДД, например: 2001-01-01")
    private String creationDate;
    @Column(name = "тип_документа")
    @Size(min = 1, max = 50, message = "Тип документа не должен быть пустым и его размер не должен привышать 50 символов")
    private String documentType;

    public Document(int documentNumber, String name, String creationDate, String documentType) {
        this.documentNumber = documentNumber;
        this.name = name;
        this.creationDate = creationDate;
        this.documentType = documentType;
    }

    public Document() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", documentNumber='" + documentNumber + '\'' +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", documentType='" + documentType + '\'' +
                '}';
    }
}
