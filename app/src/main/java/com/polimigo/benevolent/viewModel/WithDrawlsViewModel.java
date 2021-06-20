package com.polimigo.benevolent.viewModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.polimigo.benevolent.BR;
import com.polimigo.benevolent.helpers.SharedPrefrenceHelper;
import com.polimigo.benevolent.models.DepositModel;
import com.polimigo.benevolent.models.WithdrawalModel;
import com.polimigo.benevolent.repositories.WithDrawlsRepository;
import com.polimigo.benevolent.views.events.BaseEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WithDrawlsViewModel extends BaseObservable {

    private Context context;
    private WithdrawalModel withdrawalModel;
    private BaseEvent baseEvent;
    private WithDrawlsRepository withDrawlsRepository;
    private String selectedName;
    private double selectedValue;
    private SharedPrefrenceHelper sharedPrefrenceHelper;

    private String month_name;
    Calendar cal= Calendar.getInstance();
    SimpleDateFormat month_date = new SimpleDateFormat("MMMM");

    @Bindable
    private String toastMessage = null;

    public WithDrawlsViewModel(Context context, String SelectedName, double selectedValue,WithdrawalModel withdrawalModel) {
        this.context = context;
        this.selectedName = SelectedName;
        this.selectedValue = selectedValue;
        this.withdrawalModel  = withdrawalModel;
        withDrawlsRepository = WithDrawlsRepository.newInstance();
        sharedPrefrenceHelper = new SharedPrefrenceHelper();
        month_name = month_date.format(cal.getTime());

    }

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public WithdrawalModel getWithdrawalModel() {
        return withdrawalModel;
    }

    public void setWithdrawalModel(WithdrawalModel withdrawalModel) {
        this.withdrawalModel = withdrawalModel;
    }

    public double getSelectedValue() {
        return selectedValue;
    }


    public void setSelectedValue(double selectedValue) {
        this.selectedValue = selectedValue;
    }

    public String getSelectedName() {
        return selectedName;
    }

    public void setSelectedName(String selectedName) {
        this.selectedName = selectedName;
    }

    public void saveWithdrawls() {
        float dep = sharedPrefrenceHelper.getDeposit(context);
        float withdrawal = sharedPrefrenceHelper.getWithDrawl(context);
        String message = withdrawalModel.getMonth_name();

        if (dep - withdrawal < selectedValue){
            setToastMessage("Money in the safe is not enough");
        }
        else if (message.equals(month_name)){
            setToastMessage("You took your money this month");
        }else{
            WithdrawalModel model = new WithdrawalModel();
            model.setValue(selectedValue);
            model.setCustomerId(selectedName);
            withDrawlsRepository.createDocument(model, context);
        }
    }
}
