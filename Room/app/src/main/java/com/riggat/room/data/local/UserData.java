package com.riggat.room.data.local;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.riggat.room.data.remote.models.response.LoginResponse;

public class UserData {
    public static void saveUserData(LoginResponse data, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_DB", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ACCESS_TOKEN", data.getAccessToken());
        editor.putString("REFRESH_TOKEN", data.getRefreshToken());
        editor.apply();
    }

    public static String setAccessToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_DB", MODE_PRIVATE);
        return sharedPreferences.getString("ACCESS_TOKEN", "");
    }
}
