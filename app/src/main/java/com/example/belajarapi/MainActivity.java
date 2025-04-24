package com.example.belajarapi;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }

    private RecyclerView recyclerView;
    private TeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

        recyclerView = findViewById(R.id.rvTeam);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        Log.d("TESTING_LOG", "Masuk onResponse");


        apiService.getAllTeams("English Premier League").enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<TeamModel> teamModelList = response.body().getTeams();
                    adapter = new TeamAdapter(MainActivity.this, teamModelList);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Log.e("MainActivity", "Gagal fetch data: "+ t.getMessage());
            }
        });
    }
}