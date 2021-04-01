package com.polimigo.benevolent.database;

import android.app.Person;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.polimigo.benevolent.models.Customer;

import java.util.List;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM customer")
    List<Customer> loadAllPersons();

    @Insert
    void insertPerson(Customer customer);

    @Update
    void updatePerson(Customer customer);

    @Delete
    void delete(Customer customer);

    @Query("SELECT * FROM customer WHERE id = :id")
    Customer loadPersonById(int id);

}
