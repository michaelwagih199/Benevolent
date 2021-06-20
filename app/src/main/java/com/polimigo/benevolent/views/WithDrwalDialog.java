package com.polimigo.benevolent.views;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.polimigo.benevolent.R;
import com.polimigo.benevolent.databinding.ActivityWithDrwalDialogBinding;
import com.polimigo.benevolent.helpers.Constants;
import com.polimigo.benevolent.helpers.SharedPrefrenceHelper;
import com.polimigo.benevolent.repositories.WithDrawlsRepository;
import com.polimigo.benevolent.viewModel.WithDrawlsViewModel;
import com.polimigo.benevolent.views.events.BaseEvent;

public class WithDrwalDialog extends Activity implements BaseEvent {
    private ActivityWithDrwalDialogBinding binding;
    View llProgressBar;
    Context context;
    String customerId;
    double value;
    WithDrawlsRepository withDrawlsRepository;
    private SharedPrefrenceHelper sharedPrefrenceHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_with_drwal_dialog);
        Intent intent = getIntent();
        customerId = intent.getStringExtra(Constants.Person_Id);
        value = intent.getDoubleExtra(Constants.Person_VALUE,0.0);
        withDrawlsRepository = WithDrawlsRepository.newInstance();
        llProgressBar = findViewById(R.id.llProgressBar);
        sharedPrefrenceHelper = new SharedPrefrenceHelper();
        populateData();
    }

    private void populateData() {
        double total = sharedPrefrenceHelper.getDeposit(context)  ;
        Log.e("ff",""+sharedPrefrenceHelper.getDeposit(context));
        Log.e("cccccc",""+sharedPrefrenceHelper.getWithDrawl(context));
        withDrawlsRepository.getByCustomerId(context,customerId,this,binding,value);
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
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