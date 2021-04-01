package com.polimigo.benevolent.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.polimigo.benevolent.R;
import com.polimigo.benevolent.helpers.SharedPrefrenceHelper;

public class SplashScreen extends AppCompatActivity {

    private SharedPrefrenceHelper sharedPrefrenceHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPrefrenceHelper = new SharedPrefrenceHelper();
        Log.e("tag",sharedPrefrenceHelper.getUsername(this));
        Log.e("tag",sharedPrefrenceHelper.getPassword(this));
        if (sharedPrefrenceHelper.getUsername(this).equals("default")){
            sharedPrefrenceHelper.setUsername(this,"SuperAdmin");
            sharedPrefrenceHelper.setPassword(this,"Admin@123");
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Thread timer =new Thread(){
                public void run(){
                    try {
                        sleep(2000);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            Intent i = new Intent(SplashScreen.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            };
            timer.start();
        }
        else{

        }
    }
}