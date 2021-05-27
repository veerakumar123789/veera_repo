package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class main2 extends AppCompatActivity {


        private LinearLayoutManager layoutManager;
        private List<User> userList =null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Log.i("autolog", "onCreate");
            getUserList();
        }
        private void getUserList() {
            Log.i("autolog", "getUserList");
            try {
                String url = "https://right1webapi.azurewebsites.net/api/external/stock/";
                Log.i("autolog", "https://right1webapi.azurewebsites.net/api/external/stock/");

                Retrofit retrofit = null;
                Log.i("autolog", "retrofit");

                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Log.i("autolog", "build();");
                }


                APIService service = retrofit.create(APIService.class);
                Log.i("autolog", " APIService service = retrofit.create(APIService.class);");


                Call<List<User>> call = service.getUserData();
                Log.i("autolog", "Call<List<User>> call = service.getUserData();");

                call.enqueue(new Callback<List<User>>() {

                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        //Log.i("onResponse", response.message());
                        Log.i("autolog", "onResponse");

                        userList = response.body();
                        Log.i("autolog", "List<User> userList = response.body();");

//                        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
//                        Log.i("autolog", "RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);");

                        layoutManager = new LinearLayoutManager(main2.this);
                        Log.i("autolog", "layoutManager = new LinearLayoutManager(MainActivity.this);");
//                        recyclerView.setLayoutManager(layoutManager);
//                        Log.i("autolog", "recyclerView.setLayoutManager(layoutManager);");
//
//                        RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), userList);
//                        Log.i("autolog", "RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), userList);");
//                        recyclerView.setAdapter(recyclerViewAdapter);
                        Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.i("autolog", t.getMessage());
                    }
                });
            }catch (Exception e) {Log.i("autolog", "Exception");}
        }

    }
