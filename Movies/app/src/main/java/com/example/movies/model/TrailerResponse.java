package com.example.movies.model;

import com.google.gson.annotations.SerializedName;

public class TrailerResponse {
    @SerializedName("videos")
    private TrailerList trailerList;

    public TrailerList getTrailerList() {
        return trailerList;
    }

    public TrailerResponse(TrailerList trailerList) {
        this.trailerList = trailerList;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "trailerList=" + trailerList +
                '}';
    }
}
