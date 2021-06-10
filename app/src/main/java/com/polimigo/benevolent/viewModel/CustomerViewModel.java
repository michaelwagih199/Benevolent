package com.polimigo.benevolent.viewModel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.polimigo.benevolent.BR;
import com.polimigo.benevolent.helpers.Constants;
import com.polimigo.benevolent.models.Customer;
import com.polimigo.benevolent.repositories.CustomerRepository;
import com.polimigo.benevolent.views.events.BaseEvent;

public class CustomerViewModel extends BaseObservable {
    private Context context;
    private BaseEvent baseEvent;
    private String successMessage = "Register Saved Successfully";
    private String validateMessage = "Please add all data";
    private String errorMessage = "Saved Failed";
    private Customer customer;
    private String saveRegisterCheck;
    private CustomerRepository customerRepository;

    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public CustomerViewModel(Context context, Customer customer, BaseEvent baseEvent, String saveRegisterCheck) {
        this.context = context;
        this.customer = customer;
        this.baseEvent = baseEvent;
        this.saveRegisterCheck = saveRegisterCheck;
        customerRepository = CustomerRepository.newInstance();

    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getSaveRegisterCheck() {
        return saveRegisterCheck;
    }

    public void setSaveRegisterCheck(String saveRegisterCheck) {
        this.saveRegisterCheck = saveRegisterCheck;
    }

    public void onAddRegister() {
        if (isInputDataValid()) {
            Customer model = new Customer();
            if (saveRegisterCheck.equals("Save Register")){
                baseEvent.onStartedL();
                model.setName(customer.getName());
                model.setNotes(customer.getNotes());
                model.setNumber(customer.getNumber());
                model.setValue(customer.getValue());
                customerRepository.createDocument(model, context);
            }else {
                baseEvent.onStartedL();
                customerRepository.updateContact(customer,context);
            }

        } else {
            setToastMessage(validateMessage);
            baseEvent.onFailerL();
        }
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(customer.getName()) &&
                !TextUtils.isEmpty(customer.getNumber()) &&
                !TextUtils.isEmpty(customer.getNotes());
    }

}
