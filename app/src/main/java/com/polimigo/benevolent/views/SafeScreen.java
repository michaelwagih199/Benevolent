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
import com.polimigo.benevolent.databinding.ActivitySafeScreenBinding;
import com.polimigo.benevolent.helpers.SharedPrefrenceHelper;
import com.polimigo.benevolent.models.DepositModel;
import com.polimigo.benevolent.models.WithdrawalModel;
import com.polimigo.benevolent.repositories.CustomerRepository;
import com.polimigo.benevolent.repositories.DopositRepository;
import com.polimigo.benevolent.repositories.WithDrawlsRepository;
import com.polimigo.benevolent.viewModel.SafeViewModel;

import java.util.concurrent.atomic.AtomicReference;

public class SafeScreen extends AppCompatActivity {
    private ActivitySafeScreenBinding binding;
    private CustomerRepository customerRepository;
    private DopositRepository dopositRepository;
    private SharedPrefrenceHelper sharedPrefrenceHelper;
    private WithDrawlsRepository withDrawlsRepository;
    private SafeViewModel safeViewModel;

    float deposit = 0;
    float withdrawal = 0;
    int customerNumber = 0;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customerRepository = CustomerRepository.newInstance();
        dopositRepository = DopositRepository.newInstance();
        withDrawlsRepository = WithDrawlsRepository.newInstance();
        sharedPrefrenceHelper = new SharedPrefrenceHelper();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_safe_screen);
        dopositRepository.getAllContacts(task -> {
            task.getResult().getDocuments().forEach(d -> {
                deposit += d.toObject(DepositModel.class).getValue();
                sharedPrefrenceHelper.setDeposit(this, deposit);
                Log.i("deposit", "" + deposit);

            });
        });

        withDrawlsRepository.getAllContacts(task -> {
            task.getResult().getDocuments().forEach(d -> {
                withdrawal += d.toObject(WithdrawalModel.class).getValue();
                Log.i("withdrawal", "" + withdrawal);
                sharedPrefrenceHelper.setWithDrwal(this, withdrawal);
            });

        });

        customerRepository.getAllContacts(task -> {
            customerNumber = task.getResult().getDocuments().size();
            safeViewModel = new SafeViewModel(this,
                    task.getResult().getDocuments().size(),
                    sharedPrefrenceHelper.getDeposit(this), sharedPrefrenceHelper.getWithDrawl(this));
            binding.setViewModel(safeViewModel);
            binding.executePendingBindings();
        });

    }


    public void toDeposit(View view) {
        startActivity(new Intent(this, DepositActivity.class));
    }

    public void toWithDrawl(View view) {
        startActivity(new Intent(this, WithdrawalActivity.class));
    }
}