package com.example.movies.data;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movies.model.Movie;

@Database(entities = {Movie.class},version = 1)
public abstract class MoviesDatabase extends RoomDatabase {
    private static MoviesDatabase instance=null;
    private static final String BASE_URL="movies.db";

    public static MoviesDatabase getInstance(Application application){
        if (instance==null){
            instance= Room.databaseBuilder(application,MoviesDatabase.class,BASE_URL)
                    .build();
        }
        return instance;
    }

    public abstract MovieDao movieDao();
}
