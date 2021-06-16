package com.polimigo.benevolent.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.polimigo.benevolent.R;
import com.polimigo.benevolent.databinding.ActivitySafeScreenBindingImpl;
import com.polimigo.benevolent.models.DepositModel;
import com.polimigo.benevolent.repositories.CustomerRepository;
import com.polimigo.benevolent.repositories.DopositRepository;
import com.polimigo.benevolent.viewModel.SafeViewModel;

import java.util.concurrent.atomic.AtomicReference;

public class SafeScreen extends AppCompatActivity {
    ActivitySafeScreenBindingImpl binding;
    private CustomerRepository customerRepository;
     DopositRepository dopositRepository;
    private SafeViewModel safeViewModel;
    double value= 0.0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customerRepository = CustomerRepository.newInstance();
        dopositRepository = DopositRepository.newInstance();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_safe_screen);

        dopositRepository.getAllContacts(task -> {
            task.getResult().getDocuments().forEach(d->{
                Log.e("dd",d.toObject(DepositModel.class).toString());
                value += d.toObject(DepositModel.class).getValue();
            });
        });

        customerRepository.getAllContacts(task -> {
            safeViewModel = new SafeViewModel(this,
                    task.getResult().getDocuments().size(),
                    value);
            binding.setViewModel(safeViewModel);
        });




    }


    public void toDeposit(View view) {
        startActivity(new Intent(this,DepositActivity.class));
    }
}