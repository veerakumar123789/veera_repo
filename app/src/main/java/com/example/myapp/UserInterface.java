package com.example.myapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserInterface {


    @POST(".")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
