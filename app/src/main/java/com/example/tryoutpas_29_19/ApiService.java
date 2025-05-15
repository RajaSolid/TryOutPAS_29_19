package com.example.tryoutpas_29_19;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search_all_teams.php")
    Call<TeamsResponse> getTeams(@Query("l") String leagueName);
}
