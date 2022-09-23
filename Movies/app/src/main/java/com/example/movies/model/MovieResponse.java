package com.example.movies.model;

import com.example.movies.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("docs")
    private List<Movie> response;

    public MovieResponse(List<Movie> response) {
        this.response = response;
    }

    public List<Movie> getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "response=" + response +
                '}';
    }
}
