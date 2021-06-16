package com.polimigo.benevolent.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.polimigo.benevolent.R;
import com.polimigo.benevolent.databinding.ActivityDepositBinding;
import com.polimigo.benevolent.models.DepositModel;
import com.polimigo.benevolent.viewModel.DepositViewModel;
import com.polimigo.benevolent.views.events.BaseEvent;

public class DepositActivity extends AppCompatActivity implements BaseEvent {

    private ActivityDepositBinding binding;
    private DepositModel depositModel;
    View llProgressBar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_deposit);
        DepositModel depositModel = new DepositModel();
        depositModel.setComment("");
        depositModel.setValue(0.0);
        DepositViewModel depositViewModel = new DepositViewModel(context, depositModel, this);
        binding.setViewModel(depositViewModel);
        binding.executePendingBindings();
        llProgressBar = findViewById(R.id.llProgressBar);
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