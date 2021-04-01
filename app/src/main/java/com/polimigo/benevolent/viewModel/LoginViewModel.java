package com.polimigo.benevolent.viewModel;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.polimigo.benevolent.BR;
import com.polimigo.benevolent.helpers.SharedPrefrenceHelper;
import com.polimigo.benevolent.views.AboutApplication;
import com.polimigo.benevolent.views.MainActivity;
import com.polimigo.benevolent.views.events.BaseEvent;

public class LoginViewModel extends BaseObservable {
    private String userName;
    private String password;
    private Context context;
    private BaseEvent baseEvent;
    private String successMessage = "Login was successful";
    private String validateMessage = "Please add all data";
    private String errorMessage = "Failed Login";

    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public LoginViewModel(Context context, BaseEvent baseEvent) {
        this.context = context;
        this.baseEvent = baseEvent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void onAboutApplication(){
        context.startActivity(new Intent(context.getApplicationContext(), AboutApplication.class));
    }

    public void onLoginClicked() {
        if (isInputDataValid()) {
            baseEvent.onStartedL();
            if (isUser()){
                baseEvent.onSuccessL();
                context.startActivity(new Intent(context.getApplicationContext(), MainActivity.class));
            }
            else {
                setToastMessage(errorMessage);
                baseEvent.onFailerL();
            }
        } else{
            setToastMessage(validateMessage);
            baseEvent.onFailerL();
        }
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(userName) &&
                !TextUtils.isEmpty(password);
    }

    public boolean isUser() {
        boolean result = false;
        SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();
        if (sharedPrefrenceHelper.getUsername(context).equals(userName)
                && sharedPrefrenceHelper.getPassword(context).equals(password))
            result = true;
        else
            result = false;
        return result;
    }
}
