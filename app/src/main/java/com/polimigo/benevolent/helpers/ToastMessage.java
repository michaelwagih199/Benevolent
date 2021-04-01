package com.polimigo.benevolent.helpers;

import android.content.Context;
import android.widget.Toast;

public class ToastMessage {

   public static void addMessage(String message, Context context){
        Toast.makeText(context.getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }
}
