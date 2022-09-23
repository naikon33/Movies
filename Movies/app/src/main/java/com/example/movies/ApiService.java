package com.example.movies;

import com.example.movies.model.MovieResponse;
import com.example.movies.model.ReviewResponse;
import com.example.movies.model.TrailerResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie?token=0990YWD-VBE4YBC-HVP06GY-JPGHKCE&field=rating.kp&search=7-10&sortField=votes.imdb&sortType=-1&limit=40")
    Single<MovieResponse> loadMovie(@Query("page") int page);

    @GET("movie?token=0990YWD-VBE4YBC-HVP06GY-JPGHKCE&field=id")
    Single<TrailerResponse> loadTrailer(@Query("search") int id);

    @GET("review?token=0990YWD-VBE4YBC-HVP06GY-JPGHKCE&field=movieId&sortField=votes.imdb&sortType=1")
    Single<ReviewResponse> loadReview(@Query("search") int id);
}
