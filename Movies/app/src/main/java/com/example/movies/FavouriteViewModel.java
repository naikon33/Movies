package com.example.movies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movies.data.MovieDao;
import com.example.movies.data.MoviesDatabase;
import com.example.movies.model.Movie;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {
    private MovieDao movieDao;
    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        movieDao= MoviesDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Movie>> getAllMovies(){
        return movieDao.getAll();
    }
}
