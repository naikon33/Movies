package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.movies.adapter.MoviesAdapter;
import com.example.movies.model.Movie;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView recycleFavourite;

    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        moviesAdapter=new MoviesAdapter();
        initViews();
        FavouriteViewModel viewModel=new ViewModelProvider(this).get(FavouriteViewModel.class);
        recycleFavourite.setAdapter(moviesAdapter);
        recycleFavourite.setLayoutManager(new GridLayoutManager(this,2));
        viewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMovies(movies);
            }
        });
        moviesAdapter.setOnClickDetail(new MoviesAdapter.onClickDetail() {
            @Override
            public void onClick(Movie movie) {
                Intent intent=MovieDetailActivity.newIntent(FavoritesActivity.this,movie);
                startActivity(intent);
            }
        });
    }

    public static Intent newIntent(Context context){
        return new Intent(context,FavoritesActivity.class);
    }

    private void initViews() {
        recycleFavourite=findViewById(R.id.recycleFavourite);
    }
}