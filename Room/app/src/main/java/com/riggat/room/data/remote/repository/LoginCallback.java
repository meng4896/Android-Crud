package com.riggat.room.data.remote.repository;

import com.riggat.room.data.remote.models.response.LoginResponse;

public interface LoginCallback {
    void onLogin();
    void onSuccess(LoginResponse response);
    void onError(String message);
}
