package com.polimigo.benevolent.helpers;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrenceHelper {

    private String PREF_NAME = "prefs";
    private SharedPreferences getPrefs(Context context)  {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

   public void setUsername(Context context , String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("userName", input);
        editor.commit();
    }

    public void setPassword(Context context , String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("password", input);
        editor.commit();
    }

    public String getUsername(Context context) {
        return getPrefs(context).getString("userName", "default");
    }

    public String getPassword(Context context) {
        return getPrefs(context).getString("password", "default");
    }
}


