package com.example.androidsharepreference.app;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsharepreference.activity.LoginActivity;
import com.example.androidsharepreference.data.local.UserSharePreference;
import com.example.androidsharepreference.utils.MessageUtils;

public class BaseActivity extends AppCompatActivity {
    // Check user login
    // Android Lifecycle

    @Override
    protected void onResume() {
        super.onResume();
        if (false == UserSharePreference.checkUserLogin(this)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    protected void logout(){
        MessageUtils.showMessage("Logout success", this);
        UserSharePreference.removeUser(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
