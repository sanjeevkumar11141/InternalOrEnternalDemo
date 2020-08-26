package com.sanju007.internalorexternalsk;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

   static SharedPreferences sharedPreferences = null;
    static SharedPreferences.Editor editor = null;
    static Prefs prefs = null;

    public static Prefs getInstance(Context context){

        if(prefs==null){
            prefs = new Prefs();

            sharedPreferences = context.getSharedPreferences("myPrefs", context.MODE_PRIVATE);
        }
        return prefs;
    }
}
