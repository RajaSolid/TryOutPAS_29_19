package com.example.tryoutpas_29_19;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamsResponse {
        @SerializedName("teams")
        private List<Teams> teams;

        public List<Teams> getTeams() {
            return teams;
        }
}
