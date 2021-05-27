package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    EditText ed1,ed2;
    Button btnLogin;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ed1 = findViewById(R.id.edUsername);
        ed2 = findViewById(R.id.password);
        btnLogin = findViewById(R.id.forgot_button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(ed1.getText().toString()) || TextUtils.isEmpty(ed2.getText().toString())){
                    Toast.makeText(Home.this,"Username / Password Required", Toast.LENGTH_LONG).show();
                }else{
                    //proceed to login
                    login();
                }

            }
        });
    }


    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(ed1.getText().toString());
        loginRequest.setPassword(ed2.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(Home.this,"Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            startActivity(new Intent(Home.this,home3.class).putExtra("data",loginResponse.getUsername()));
                        }
                    },700);

                }else{
                    Toast.makeText(Home.this,"Login Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Home.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }


}