package com.example.myapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @POST("dds861/e5f0ae4e99ff820e003c38f35276d4ce/raw/35f7b1452b3cfd55009416c62b055592858b9177/json1.json")
    Call<List<User>> getUserData();
}
