package com.polimigo.benevolent.viewModel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.polimigo.benevolent.repositories.CustomerRepository;

public class SafeViewModel extends BaseObservable {
    private Context context;
    private int customersCount;
    private float depositAmount;
    private float withdrawAmount;

    public SafeViewModel(Context context, int customersCount, float depositAmount, float withdrawAmount) {
        this.context = context;
        this.customersCount = customersCount;
        this.withdrawAmount = withdrawAmount;
        this.depositAmount = depositAmount;
    }

    public String getTotal(){
        float result =  depositAmount - withdrawAmount;
        return ""+result;
    }

    public int getCustomersCount() {
        return customersCount;
    }

    public void setCustomersCount(int customersCount) {
        this.customersCount = customersCount;
    }

    public float getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(float depositAmount) {
        this.depositAmount = depositAmount;
    }

    public float getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(float withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
}

