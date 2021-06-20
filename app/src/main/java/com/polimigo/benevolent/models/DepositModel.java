package com.polimigo.benevolent.models;

import com.google.firebase.firestore.DocumentId;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DepositModel {
    @DocumentId
    private String documentId;
    private String comment;
    private Double value;
    private String date;

    public DepositModel() {
        date= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

    public DepositModel(String documentId, Double value, String comment) {
        this.documentId = documentId;
        this.value = value;
        this.comment = comment;
        date= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "DepositModel{" +
                "documentId='" + documentId + '\'' +
                ", comment='" + comment + '\'' +
                ", value=" + value +
                ", date='" + date + '\'' +
                '}';
    }

}
