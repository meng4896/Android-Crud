package com.example.androidsharepreference.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public final class UserSharePreference {

    private static final String KEY_DATA = "USER_SESSION";
    private static final String KEY_USER_NAME = "USER_NAME";
    private static final String KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN";
    public static void addUSer(String username, String accessToken, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_DATA, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_NAME, username);
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.apply();
    }

    public static void removeUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_DATA, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USER_NAME);
        editor.remove(KEY_ACCESS_TOKEN);
        editor.apply();
    }

    public static boolean checkUserLogin(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_DATA, 0);
        String username = sharedPreferences.getString(KEY_USER_NAME, "");
        return !username.isEmpty();
    }
}
