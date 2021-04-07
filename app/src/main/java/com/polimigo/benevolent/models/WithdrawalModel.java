package com.polimigo.benevolent.models;

import com.google.firebase.firestore.DocumentId;

import java.util.Date;

public class WithdrawalModel {
    @DocumentId
    private String documentId;
    private Double value;
    private Date createdAt;
    private String customerId;

    public WithdrawalModel(String documentId, Double value, Date createdAt, String customerId) {
        this.documentId = documentId;
        this.value = value;
        this.createdAt = createdAt;
        this.customerId = customerId;
    }

    public WithdrawalModel() {
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "WithdrawalModel{" +
                "documentId='" + documentId + '\'' +
                ", value=" + value +
                ", createdAt=" + createdAt +
                ", customerId='" + customerId + '\'' +
                '}';
    }


}
