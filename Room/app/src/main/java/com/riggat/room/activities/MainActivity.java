package com.riggat.room.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.riggat.room.R;
import com.riggat.room.data.remote.repository.AuthRepository;

public class MainActivity extends AuthenticationActivity {
    private AuthRepository authRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
//        authRepository = new AuthRepository();
//        authRepository.login("riggatson", "bbu123", new LoginCallback() {
//            @Override
//            public void onLogin() {
//                showMessage("Loding Screen....");
//            }
//
//            @Override
//            public void onSuccess(LoginResponse response) {
//                showMessage("Login Success");
//            }
//
//            @Override
//            public void onError(String message) {
//                showMessage((message));
//            }
//        });

//    }
////    private void showMessage(String message){
////        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
////    }
//}