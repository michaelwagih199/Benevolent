package com.polimigo.benevolent.models;
import com.google.firebase.firestore.DocumentId;
import java.util.Date;
import java.util.Objects;

public class DepositModel {
    @DocumentId
    private String documentId;
    private Double value;
    private Date createdAt;

    public DepositModel() {
    }

    public DepositModel(String documentId, Double value, Date createdAt) {
        this.documentId = documentId;
        this.value = value;
        this.createdAt = createdAt;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "DepositModel{" +
                "documentId='" + documentId + '\'' +
                ", value=" + value +
                ", createdAt=" + createdAt +
                '}';
    }

}
