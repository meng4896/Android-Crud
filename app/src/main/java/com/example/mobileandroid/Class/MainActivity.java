package com.example.mobileandroid.Class;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.mobileandroid.Class.adapters.CustomUserAdapter;
import com.example.mobileandroid.Class.models.Role;
import com.example.mobileandroid.Class.models.User;
import com.example.mobileandroid.Class.services.UserService;
import com.example.mobileandroid.Class.services.impl.UserServiceImpl;
import com.example.mobileandroid.R;

public class MainActivity extends Base {

    private static final int RESULT_CODE_DATA = 2000;
    private UserService userService;
    private ListView listViewUser;
    private TextView tvAddUser;
    private CustomUserAdapter customUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAddUser = findViewById(R.id.tvAddUser);
        listViewUser = findViewById(R.id.lvUser);
        userService = new UserServiceImpl();

        getUserData();

        tvAddUser.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserFormActivity.class);
            startActivityForResult(intent, RESULT_CODE_DATA);
        });
    }

    private void getUserData() {
        customUserAdapter = new CustomUserAdapter(this, userService.getAllUser(), new CustomUserAdapter.OnClickListener() {
            @Override
            public void onEdit(View view, User user) {
                Intent intent = new Intent(MainActivity.this, UserFormActivity.class);
                intent.putExtra("ID", user.getId());
                intent.putExtra("USERNAME", user.getName());
                intent.putExtra("EMAIL", user.getEmail());
                intent.putExtra("GENDER", user.getGender());
                intent.putExtra("ROLE_ID", user.getRole().getId());
                startActivityForResult(intent, RESULT_CODE_DATA);
            }
        });

        listViewUser.setAdapter(customUserAdapter);

        // Delete user on long click
        listViewUser.setOnItemLongClickListener((parent, view, position, id) -> {
            User user = (User) parent.getItemAtPosition(position);
            userService.removeUser(user.getId());
            showToastMessage("User deleted");
            getUserData();
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_CODE_DATA && resultCode == RESULT_OK && data != null) {
            int id = data.getIntExtra("ID", 0);
            String username = data.getStringExtra("USERNAME");
            String email = data.getStringExtra("EMAIL");
            String gender = data.getStringExtra("GENDER");
            int roleId = data.getIntExtra("ROLE_ID", 0);
            Role role = userService.getRoleById(roleId);

            User user = new User();
            user.setId(id);
            user.setName(username);
            user.setEmail(email);
            user.setGender(gender);
            user.setRole(role);

            if (id > 0) {
                userService.modifyUser(user);
                showToastMessage("User updated successfully!");
            } else {
                userService.insertUser(user);
                showToastMessage("User added successfully!");
            }

            getUserData();
        }
    }
}
