package com.riggat.room.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.riggat.room.R;
import com.riggat.room.data.local.UserData;
import com.riggat.room.data.remote.models.response.LoginResponse;
import com.riggat.room.data.remote.repository.AuthRepository;
import com.riggat.room.data.remote.repository.LoginCallback;
import com.riggat.room.utils.MessageUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPasssword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private AuthRepository authRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        authRepository = new AuthRepository();
        etUsername = findViewById(R.id.edtUsername);
        etPasssword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void login(){
        String username = etUsername.getText().toString().trim();
        String password = etPasssword.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty()){
            MessageUtils.showMessage("Username and Password are empty", this);
            return;
        }
        authRepository.login(username, password, new LoginCallback() {
            @Override
            public void onLogin() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(LoginResponse response) {
                progressBar.setVisibility(View.GONE);
                UserData.saveUserData(response, LoginActivity.this);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(String message) {
                MessageUtils.showMessage(message,LoginActivity.this);
            }
        });
    }
}