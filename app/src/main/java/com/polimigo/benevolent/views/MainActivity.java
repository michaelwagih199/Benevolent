package com.polimigo.benevolent.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.polimigo.benevolent.R;
import com.polimigo.benevolent.database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}