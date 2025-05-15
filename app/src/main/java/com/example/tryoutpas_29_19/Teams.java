package com.example.tryoutpas_29_19;

import com.google.gson.annotations.SerializedName;

public class Teams {
    @SerializedName("strTeam")
    private String name;

    @SerializedName("strStadium")
    private String stadium;

    @SerializedName("strBadge")
    private String badgeUrl;

    public String getName() { return name; }
    public String getStadium() { return stadium; }
    public String getBadgeUrl() { return badgeUrl; }
}


