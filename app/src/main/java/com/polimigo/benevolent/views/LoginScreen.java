package com.polimigo.benevolent.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.polimigo.benevolent.R;
import com.polimigo.benevolent.databinding.ActivityLoginScreenBinding;
import com.polimigo.benevolent.viewModel.LoginViewModel;
import com.polimigo.benevolent.views.events.BaseEvent;

public class LoginScreen extends AppCompatActivity implements BaseEvent {
    View llProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginScreenBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen);
        activityMainBinding.setViewModel(new LoginViewModel(this,this));
        activityMainBinding.executePendingBindings();
        llProgressBar = findViewById(R.id.llProgressBar);
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
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

    }

}