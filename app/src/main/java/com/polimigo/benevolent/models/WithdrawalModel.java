package com.polimigo.benevolent.models;

import com.google.firebase.firestore.DocumentId;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WithdrawalModel {

    @DocumentId
    private String documentId;
    private Double value;
    private String date;
    private String customerId;
    private String month_name;
    Calendar cal= Calendar.getInstance();
    SimpleDateFormat month_date = new SimpleDateFormat("MMMM");

    public WithdrawalModel(String documentId, Double value, String customerId) {
        this.documentId = documentId;
        this.value = value;
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        month_name = month_date.format(cal.getTime());
        this.customerId = customerId;
    }

    public WithdrawalModel() {
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        month_name = month_date.format(cal.getTime());
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMonth_name() {
        return month_name;
    }

    public void setMonth_name(String month_name) {
        this.month_name = month_name;
    }

    @Override
    public String toString() {
        return "WithdrawalModel{" +
                "documentId='" + documentId + '\'' +
                ", value=" + value +
                ", date='" + date + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }

}
