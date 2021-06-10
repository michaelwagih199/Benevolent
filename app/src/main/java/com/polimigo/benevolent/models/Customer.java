package com.polimigo.benevolent.models;
import com.google.firebase.firestore.DocumentId;

public class Customer {
    @DocumentId
    private String documentId;
    private String name;
    private String number;
    private String notes;
    private Double value;

    public Customer(String documentId, String name, String number, String notes, Double value) {
        this.documentId = documentId;
        this.name = name;
        this.number = number;
        this.notes = notes;
        this.value = value;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "documentId='" + documentId + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", notes='" + notes + '\'' +
                ", value=" + value +
                '}';
    }

}
