package com.example.androidsharepreference.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidsharepreference.R;
import com.example.androidsharepreference.data.local.UserSharePreference;
import com.example.androidsharepreference.utils.MessageUtils;

public class LoginActivity extends AppCompatActivity {

    private String usernameDB = "bbu";
    private String passwordDB = "bbu123";
    private EditText etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void initView() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    public void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty()) {
            MessageUtils.showMessage("Please enter your username", this);
            return;
        }
        if (password.isEmpty()) {
            MessageUtils.showMessage("Please enter your password", this);
            return;
        }
        if (!usernameDB.equals(username)) {
            MessageUtils.showMessage("Your username is incorrect", this);
            return;
        }
        if (!passwordDB.equals(password)) {
            MessageUtils.showMessage("Your password is incorrect", this);
            return;
        }

        UserSharePreference.addUSer(username, password, this);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
