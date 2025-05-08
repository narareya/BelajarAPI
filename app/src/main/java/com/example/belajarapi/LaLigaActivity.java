package com.example.belajarapi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaLigaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TeamAdapter adapter;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_la_liga);

        recyclerView = findViewById(R.id.rvTeam);
        progressBar = findViewById(R.id.pbLoading);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        apiService.getAllTeams("Spanish La Liga").enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                Log.d("TESTING_LOG", "Masuk onResponse");
                if (response.isSuccessful() && response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    List<TeamModel> teamModelList = response.body().getTeams();
                    adapter = new TeamAdapter(LaLigaActivity.this, teamModelList);
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