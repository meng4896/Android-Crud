package com.riggat.room.data.network;


import com.riggat.room.data.remote.models.request.LoginRequest;
import com.riggat.room.data.remote.models.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/oauth/token") // Replace with your actual login path like "api/auth/login"
    Call<LoginResponse> login(@Body LoginRequest req);
}