package com.polimigo.benevolent.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.polimigo.benevolent.R;
import com.polimigo.benevolent.databinding.ActivityCustomersScreenBinding;
import com.polimigo.benevolent.databinding.ActivityWithdrawalBinding;
import com.polimigo.benevolent.models.Customer;
import com.polimigo.benevolent.repositories.CustomerRepository;
import com.polimigo.benevolent.views.events.BaseEvent;

public class WithdrawalActivity extends AppCompatActivity implements BaseEvent {

    private ActivityWithdrawalBinding binding;
    private CustomerRepository customerRepository;
    private Customer customer;
    View llProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customerRepository = customerRepository.newInstance();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_withdrawal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.customerRecycleWithDrawel.setLayoutManager(linearLayoutManager);
        populateData();
    }

    private void populateData() {
        customerRepository.getAllCustomerInWithDrawl(this,binding.customerRecycleWithDrawel);
    }

    @Override
    public void onStartedL() {

    }

    @Override
    public void onSuccessL() {

    }

    @Override
    public void onFailerL() {

    }
}