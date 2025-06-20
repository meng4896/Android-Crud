package com.example.mobileandroid.Class.services;

import com.example.mobileandroid.Class.models.Role;
import com.example.mobileandroid.Class.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();
    void insertUser(User user);
    void modifyUser(User user);
    void removeUser(int id);
    User getUserById(int id);
    List<Role> getAllRoles();
    Role getRoleById(int roleId);
}
