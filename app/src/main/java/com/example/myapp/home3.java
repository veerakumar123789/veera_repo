package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.modal.Customer_enquiries;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home3 extends AppCompatActivity {

    CardView Stock;
    CardView Customer_enquirie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);
        Stock = findViewById(R.id.stock);
        Customer_enquirie =findViewById(R.id.customer_enquery);

        Stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login1();
            }
        });
        Customer_enquirie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login2();
            }
        });
    }

    public void login1() {

        Intent i = new Intent(home3.this, stock_shearchlist.class);
        startActivity(i);
        finish();

    }
    public void login2()
    {
        Intent i = new Intent(home3.this, Customer_enquiries.class);
        startActivity(i);
        finish();
    }

}



