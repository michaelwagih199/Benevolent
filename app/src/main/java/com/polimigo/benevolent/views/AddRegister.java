package com.polimigo.benevolent.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.polimigo.benevolent.R;
import com.polimigo.benevolent.databinding.ActivityAddRegisyterBindingImpl;
import com.polimigo.benevolent.helpers.Constants;
import com.polimigo.benevolent.models.Customer;
import com.polimigo.benevolent.repositories.CustomerRepository;
import com.polimigo.benevolent.viewModel.CustomerViewModel;
import com.polimigo.benevolent.views.events.BaseEvent;

public class AddRegister extends AppCompatActivity implements BaseEvent {
    private View llProgressBar;
    private ActivityAddRegisyterBindingImpl binding;
    private Customer customer;
    private Intent intent;
    private String saveCheck = "Save Register";
    private CustomerRepository customerRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_regisyter);
        intent = getIntent();
        customerRepository = CustomerRepository.newInstance();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Person_Id)) {
            saveCheck = "Update Register";
            updateUi(intent.getStringExtra(Constants.UPDATE_Person_Id));
        }
        customer = new Customer("", "", "", "", 0.0);
        CustomerViewModel customerViewModel = new CustomerViewModel(this, customer, this, saveCheck);
        binding.setViewModel(customerViewModel);
        binding.executePendingBindings();
        llProgressBar = findViewById(R.id.llProgressBar);
    }

    private void updateUi(String stringExtra) {
        customerRepository.getCustomerById(this,stringExtra,this,binding);
    }


    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
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