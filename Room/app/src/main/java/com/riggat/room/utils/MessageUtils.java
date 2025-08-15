package com.riggat.room.utils;

import android.content.Context;
import android.widget.Toast;

public class MessageUtils {
    public static void showMessage(String message, Context context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
