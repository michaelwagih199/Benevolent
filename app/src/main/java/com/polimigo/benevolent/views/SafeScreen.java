package com.polimigo.benevolent.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.polimigo.benevolent.R;
import com.polimigo.benevolent.databinding.ActivityLoginScreenBindingImpl;
import com.polimigo.benevolent.databinding.ActivitySafeScreenBindingImpl;
import com.polimigo.benevolent.models.Customer;
import com.polimigo.benevolent.repositories.CustomerRepository;
import com.polimigo.benevolent.viewModel.SafeViewModel;

import java.util.ArrayList;

public class SafeScreen extends AppCompatActivity {
    ActivitySafeScreenBindingImpl binding;
    private CustomerRepository customerRepository;
    private SafeViewModel safeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customerRepository = CustomerRepository.newInstance();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_safe_screen);
        customerRepository.getAllContacts(task -> {
            safeViewModel = new SafeViewModel(this,
                    task.getResult().getDocuments().size(),
                    0.0);
            binding.setViewModel(safeViewModel);
        });

    }
}