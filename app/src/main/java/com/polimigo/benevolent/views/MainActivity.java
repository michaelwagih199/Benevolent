package com.polimigo.benevolent.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.polimigo.benevolent.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toSafe(View view) {
        startActivity(new Intent(this, SafeScreen.class));
    }

    public void toPerson(View view) {
        startActivity(new Intent(this, CustomersScreen.class));
    }

}