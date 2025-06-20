package com.example.mobileandroid.Class;


import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Base extends AppCompatActivity {
    protected void showToastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
