package com.example.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerList {
    @SerializedName("trailers")
    private List<Trailer> trailerList;

    public TrailerList(List<Trailer> trailerList) {
        this.trailerList = trailerList;
    }

    public List<Trailer> getTrailerList() {
        return trailerList;
    }

    @Override
    public String toString() {
        return "TrailerList{" +
                "trailerList=" + trailerList +
                '}';
    }
}
