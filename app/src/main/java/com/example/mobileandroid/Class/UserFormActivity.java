package com.example.mobileandroid.Class;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.mobileandroid.Class.adapters.CustomRoleAdapter;
import com.example.mobileandroid.Class.models.Role;
import com.example.mobileandroid.Class.services.UserService;
import com.example.mobileandroid.Class.services.impl.UserServiceImpl;
import com.example.mobileandroid.R;

public class UserFormActivity extends Base {

    private UserService userService;
    private Spinner spinnerRole;
    private Role selectRole;
    private CustomRoleAdapter customRoleAdapter;
    private EditText etUsername, etEmail;
    private RadioButton rbMale, rbFemale;
    private Button btnCreate, btnBack;

    private int userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        initView();

        userService = new UserServiceImpl();
        customRoleAdapter = new CustomRoleAdapter(this, userService.getAllRoles());
        spinnerRole.setAdapter(customRoleAdapter);

        spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectRole = userService.getAllRoles().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        btnCreate.setOnClickListener(v -> onCreateUser());
        btnBack.setOnClickListener(v -> finish());
    }

    private void onCreateUser() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String gender = rbMale.isChecked() ? "Male" : "Female";

        if (username.isEmpty()) {
            showToastMessage("Please enter username");
            return;
        }

        if (email.isEmpty()) {
            showToastMessage("Please enter email");
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("ID", userId);
        intent.putExtra("USERNAME", username);
        intent.putExtra("EMAIL", email);
        intent.putExtra("GENDER", gender);
        intent.putExtra("ROLE_ID", selectRole.getId());

        setResult(RESULT_OK, intent);
        finish();
    }

    private void initView() {
        spinnerRole = findViewById(R.id.spinnerRole);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnCreate = findViewById(R.id.btnCreate);
        btnBack = findViewById(R.id.btnBack);

        rbMale.setChecked(true);

        // Editing existing user
        Intent intent = getIntent();
        userId = intent.getIntExtra("ID", 0);
        if (userId > 0) {
            etUsername.setText(intent.getStringExtra("USERNAME"));
            etEmail.setText(intent.getStringExtra("EMAIL"));
            String gender = intent.getStringExtra("GENDER");
            if ("Male".equalsIgnoreCase(gender)) {
                rbMale.setChecked(true);
            } else {
                rbFemale.setChecked(true);
            }

            int roleId = intent.getIntExtra("ROLE_ID", 0);
            for (int i = 0; i < userService.getAllRoles().size(); i++) {
                if (userService.getAllRoles().get(i).getId() == roleId) {
                    spinnerRole.setSelection(i);
                    break;
                }
            }
        }
    }
}
