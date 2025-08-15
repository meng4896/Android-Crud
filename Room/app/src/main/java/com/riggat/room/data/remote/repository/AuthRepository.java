package com.riggat.room.data.remote.repository;

import com.riggat.room.data.network.ApiClient;
import com.riggat.room.data.network.ApiService;
import com.riggat.room.data.remote.models.request.LoginRequest;
import com.riggat.room.data.remote.models.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final ApiService apiService;

    public AuthRepository() {

        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void login(String username, String password, LoginCallback loginCallback) {
        try {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setPhoneNumber(username);
            loginRequest.setPassword(password);
            loginCallback.onLogin();
            apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        loginCallback.onSuccess(response.body());
                    } else {
                        loginCallback.onError("Your username and password are incorrect");
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    loginCallback.onError("General error" + t.getMessage());
                }
            });
        } catch (Throwable e) {
            loginCallback.onError(e.getMessage());
        }
    }
}
