package com.example.mobileandroid.Class.services.impl;

import com.example.mobileandroid.Class.models.Role;
import com.example.mobileandroid.Class.models.User;
import com.example.mobileandroid.Class.services.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {

    public static List<User> userList = new ArrayList<>();
    private static List<Role> roleList = new ArrayList<>();
    @Override
    public List<User> getAllUser() {
        if (userList.isEmpty()) {
            userList.add(new User(
                    1, "cust", "sok@gmail.com", "Male", getRoleById(1)
            ));
            userList.add(new User(
                    2, "lucas", "dara@gmail.com", "Male", getRoleById(2)
            ));
            userList.add(new User(
                    3, "kalea", "nika@gmail.com", "Female", getRoleById(3)
            ));
            userList.add(new User(
                    4, "solin", "sokha@gmail.com", "Male", getRoleById(4)
            ));
        }
        return userList;
    }

    @Override
    public void insertUser(User user) {
        user.setId(userList.size()+1);
        userList.add(user);
    }

    @Override
    public void modifyUser(User user) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == user.getId()) {
                userList.set(i, user);
                return;
            }
        }
    }

    @Override
    public void removeUser(int id) {
        userList.removeIf(u -> u.getId() == id);
    }
    @Override
    public User getUserById(int id) {
        for (User user : userList){
            if (user.getId()==id){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        if (roleList.isEmpty()) {
            Role roleAdmin = new Role(1, "Admin");
            roleList.add(roleAdmin);

            Role roleUser = new Role(2, "User");
            roleList.add(roleUser);

            Role roleCashier = new Role(3, "Cashier");
            roleList.add(roleCashier);

            Role roleSupperAdmin = new Role(4, "Super Admin");
            roleList.add(roleSupperAdmin);
        }
        return roleList;
    }


    @Override
    public Role getRoleById(int roleId) {
        for (Role role: roleList){
            if (role.getId()==roleId){
                return role;
            }
        }
        return null;
    }
}