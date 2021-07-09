package com.example.tugasakhir;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasakhir.Adapter.ProductAdapter;
import com.example.tugasakhir.Model.Product;
import com.example.tugasakhir.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
//    Sumber yang digunakan untuk membuat tugas ini adalah
//    untuk belajar memanggil API dengan retrofit
//    https://www.codepolitan.com/rest-api-client-sederhana-dengan-retrofit-pada-android-studio-58986d62c46ae
//    untuk solusi ketika menemukan error
//    https://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
//    API public yang digunakan
//    https://fakestoreapi.com/

    private RecyclerView mRecyclerView;
    private ProductAdapter productAdapter;
    private List<Product> dataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       initViews();
    }

    private void initViews(){
        mRecyclerView=(RecyclerView) findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadJSON();
    }

    private void loadJSON(){
        dataArrayList = new ArrayList<>();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface =retrofit.create(ApiInterface.class);
        Call<List<Product>> call= apiInterface.getJSON();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                dataArrayList = response.body();
                productAdapter = new ProductAdapter(getApplicationContext(), dataArrayList);
                mRecyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }

        });
    }
}
