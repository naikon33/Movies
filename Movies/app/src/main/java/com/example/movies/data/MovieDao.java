package com.example.movies.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movies.model.Movie;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie_favourite")
    LiveData<List<Movie>> getAll();

    @Query("SELECT * FROM movie_favourite WHERE id=:movieId")
    LiveData<Movie> getFavouriteMovie(int movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMovie(Movie movie);

    @Query("DELETE FROM movie_favourite WHERE id=:movieId")
    Completable remove(int movieId);
}
