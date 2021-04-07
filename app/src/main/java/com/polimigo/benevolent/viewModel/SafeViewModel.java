package com.polimigo.benevolent.viewModel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.polimigo.benevolent.repositories.CustomerRepository;

public class SafeViewModel extends BaseObservable {
    private Context context;
    private int customersCount;
    private Double moneySum;

    public SafeViewModel(Context context, int customersCount, Double moneySum) {
        this.context = context;
        this.customersCount = customersCount;
        this.moneySum = moneySum;
    }

    public int getCustomersCount() {
        return customersCount;
    }

    public void setCustomersCount(int customersCount) {
        this.customersCount = customersCount;
    }

    public Double getMoneySum() {
        return moneySum;
    }

    public void setMoneySum(Double moneySum) {
        this.moneySum = moneySum;
    }

}

