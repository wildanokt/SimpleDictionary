package com.wildanokt.dictionarymade.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.wildanokt.dictionarymade.R;

public class AppPreference {
    SharedPreferences preferences;
    Context context;

    public AppPreference(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input){
        SharedPreferences.Editor editor = preferences.edit();
        String KEY = context.getResources().getString(R.string.app_first_run);
        editor.putBoolean(KEY, input);
        editor.apply();
    }

    public Boolean getFirstRun(){
        String KEY = context.getResources().getString(R.string.app_first_run);
        return preferences.getBoolean(KEY, true);
    }
}
