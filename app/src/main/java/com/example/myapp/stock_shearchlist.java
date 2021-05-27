package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myapp.adapter.ItemAdapter;
import com.example.myapp.interfaces.ApiResponse;
import com.example.myapp.modal.MainStockWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class stock_shearchlist extends AppCompatActivity implements ApiResponse {

    SearchView searchView;
    ItemAdapter adapter;
    private static String JSON_URL = "https://right1webapi.azurewebsites.net/api/external/stock/";
    List<StockClass> stacklist;
    RecyclerView recyclerView;
    ApiResponse apiResponse = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_shearchlist);

        stacklist = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();
        searchView = findViewById(R.id.searchView);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.isEmpty()){
                    adapter.setData(stacklist);
                } else{
                    ArrayList<StockClass> tempList = new ArrayList<>();
                    for(StockClass items : stacklist){
                        if(items.contains(query))
                            tempList.add(items);
                    }
                    adapter.setData(tempList);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty()){
                    adapter.setData(stacklist);
                } else{
                    ArrayList<StockClass> tempList = new ArrayList<>();
                    for(StockClass items : stacklist){
                        if(items.contains(newText))
                            tempList.add(items);
                    }
                    adapter.setData(tempList);
                }
                return false;
            }
        });

        getAllStocks();

    }

    private void initRecyclerView() {
        adapter = new ItemAdapter();
        adapter.setData(stacklist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSuccess(Object objectOfAny) {
        stacklist = ((MainStockWrapper) objectOfAny).getItems();
        adapter.setData(stacklist);
    }

    @Override
    public void onFail(Object objectOfAny) {
        stock_shearchlist.this.runOnUiThread(() -> Toast.makeText(stock_shearchlist.this, ((String) objectOfAny), Toast.LENGTH_SHORT).show());
    }

    private void getAllStocks(){
        Call<MainStockWrapper> loginResponseCall = ApiClient.getUserService().getStocks();
        loginResponseCall.enqueue(new Callback<MainStockWrapper>() {
            @Override
            public void onResponse(Call<MainStockWrapper> call, Response<MainStockWrapper> response) {
                apiResponse.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MainStockWrapper> call, Throwable t) {
                apiResponse.onFail(t.getLocalizedMessage());

            }
        });
    }

    public class Getdata extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {

            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;


                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);
                    int data = isr.read();
                    while (data != -1) {

                        current += (char) data;
                        data = isr.read();
                    }

                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;

        }


        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("Items");
                ArrayList<StockClass> tempList = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {

                    StockClass model = new StockClass();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    model.setProductCode(jsonObject1.getString("ProductCode"));
                    model.setProductName(jsonObject1.getString("ProductName"));
                    model.setDescription(jsonObject1.getString("Description"));
                    model.setPartNo(jsonObject1.getString("PartNo"));
                    model.setQty(jsonObject1.getString("Qty"));

                    tempList.add(model);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            apiResponse.onSuccess(stacklist);

        }


    }
}