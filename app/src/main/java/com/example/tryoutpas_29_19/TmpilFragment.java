package com.example.tryoutpas_29_19;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TmpilFragment extends Fragment {

    private RecyclerView recyclerView;
    private TeamAdapter adapter;
    private List<Teams> teams = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tmpil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TeamAdapter(teams);
        recyclerView.setAdapter(adapter);

        // Ambil LEAGUE_NAME dari arguments
        String leagueName = getArguments() != null ? getArguments().getString("LEAGUE_NAME") : null;

        if (leagueName == null || leagueName.isEmpty()) {
            Toast.makeText(getContext(), "League name is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Panggil API
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<TeamsResponse> call = apiService.getTeams(leagueName);

        call.enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    teams.clear();
                    teams.addAll(response.body().getTeams());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Response kosong atau error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal load data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
