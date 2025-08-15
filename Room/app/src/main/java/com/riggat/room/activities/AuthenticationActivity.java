package com.riggat.room.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.riggat.room.data.local.UserData;

public class AuthenticationActivity extends AppCompatActivity {
    protected void onResume(){
        super.onResume();
        if (UserData.setAccessToken(this).isEmpty()){
            Intent intent = new Intent(AuthenticationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }
    }

}
