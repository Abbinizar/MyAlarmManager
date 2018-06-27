package com.example.abbinizar.myalarmmanager;

import android.content.Context;
import android.content.SharedPreferences;

public class AlarmPreference {
    private final String PREF_NAME = "AlarmPreference";
    private final String KEY_ONETIME_DATE = "oneTimeDate";
    private final String KEY_ONETIME_TIME = "oneTimeTime";
    private final String KEY_ONETIME_MESSAGE = "oneTimeMessage";
    private final String KEY_REPEATING_TIME = "repeatingTime";
    private final String KEY_REPEATING_MESSAGE = "repeatingMessage";
    private SharedPreferences mSharePreference;
    private SharedPreferences.Editor editor;

    public AlarmPreference(Context context){
        mSharePreference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = mSharePreference.edit();
    }
    public void setOntimeDate (String date){
        editor.putString(KEY_ONETIME_DATE, date);
        editor.commit();
    }
    public String getOntimeDate(){
        return mSharePreference.getString(KEY_ONETIME_DATE, null);
    }
    public void setOntimeTime(String time){
        editor.putString(KEY_ONETIME_TIME, time);
        editor.commit();
    }
    public String getOnetimeTime() {
        return mSharePreference.getString(KEY_ONETIME_TIME, null);
    }
    public void setOntimeMessage(String message){
        editor.putString(KEY_ONETIME_MESSAGE, message);
        editor.commit();
    }
    public String getOntimeMessage(){
        return mSharePreference.getString(KEY_ONETIME_MESSAGE, null);
    }
    public void setRepeatingTime(String repeatingTime){
        editor.putString(KEY_REPEATING_MESSAGE, repeatingTime);
        editor.commit();
    }
    public String getRepeatingTime(){
        return mSharePreference.getString(KEY_REPEATING_TIME, null);
    }
    public void setRepeatingMessage(String repeatingMessage){
        editor.putString(KEY_REPEATING_MESSAGE, repeatingMessage);
        editor.commit();
    }
    public String getRepeatingMessage(){
        return mSharePreference.getString(KEY_REPEATING_MESSAGE, null);
    }
    public void clear(){
        editor.clear();
        editor.commit();
    }
}
