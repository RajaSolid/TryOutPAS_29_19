package com.example.tryoutpas_29_19;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

            private RecyclerView recyclerView;
            private ProgressBar progressBar;
            private TeamAdapter adapter;
            private List<Teams> teams = new ArrayList<>();

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                recyclerView = findViewById(R.id.recyclerView);
                progressBar = findViewById(R.id.progressBar);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new TeamAdapter(teams);
                recyclerView.setAdapter(adapter);


                String leagueName = getIntent().getStringExtra("LEAGUE_NAME");
                ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
                Call<TeamsResponse> call = apiService.getTeams(leagueName);

                call.enqueue(new Callback<TeamsResponse>() {
                    @Override
                    public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            teams.clear();
                            teams.addAll(response.body().getTeams());
                            adapter.notifyDataSetChanged();
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<TeamsResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }