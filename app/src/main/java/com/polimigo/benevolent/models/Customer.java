package com.polimigo.benevolent.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Customer {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "number")
    private String number;
    @ColumnInfo(name = "notes")
    private String notes;

    @Ignore
    public Customer(int id, String name, String number, String notes) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.notes = notes;
    }

    public Customer(String name, String number, String notes) {
        this.name = name;
        this.number = number;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
