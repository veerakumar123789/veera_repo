package com.example.myapp;

import com.example.myapp.modal.MainStockWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {



    @POST("api/external/login/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("api/external/stock/")
    Call<MainStockWrapper> getStocks();


}
