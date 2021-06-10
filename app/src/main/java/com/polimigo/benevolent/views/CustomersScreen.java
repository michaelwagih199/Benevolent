package com.polimigo.benevolent.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.polimigo.benevolent.R;
import com.polimigo.benevolent.databinding.ActivityAddRegisyterBindingImpl;
import com.polimigo.benevolent.databinding.ActivityCustomersScreenBinding;
import com.polimigo.benevolent.models.Customer;
import com.polimigo.benevolent.repositories.CustomerRepository;
import com.polimigo.benevolent.views.events.BaseEvent;

public class CustomersScreen extends AppCompatActivity implements BaseEvent {
    private ActivityCustomersScreenBinding binding;
    private CustomerRepository customerRepository;
    private Customer customer;
    View llProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customerRepository = customerRepository.newInstance();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_customers_screen);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.customerRecycle.setLayoutManager(linearLayoutManager);
        llProgressBar = findViewById(R.id.llProgressBar);
        populateData();
    }

    public void toAddRegister(View view) {
        startActivity(new Intent(this, AddRegister.class));
        finish();
    }

    private void populateData() {
        customerRepository.getAllCustomer(this,binding.customerRecycle);
    }

    @Override
    public void onStartedL() {
        llProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessL() {
        llProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailerL() {
        llProgressBar.setVisibility(View.GONE);
    }
}