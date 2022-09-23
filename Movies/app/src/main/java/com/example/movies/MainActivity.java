package com.example.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.movies.adapter.MoviesAdapter;
import com.example.movies.model.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;

    private RecyclerView recycleMovie;

    private ProgressBar progressBar;

    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        recycleMovie=findViewById(R.id.recycleMovie);
        moviesAdapter=new MoviesAdapter();
        recycleMovie.setAdapter(moviesAdapter);
        recycleMovie.setLayoutManager(new GridLayoutManager(this,2));
        mainViewModel= new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMovies(movies);
            }
        });
        mainViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading){
                    progressBar.setVisibility(View.VISIBLE);
                }
                else{
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        moviesAdapter.setReadEndListener(new MoviesAdapter.onReadEndListener() {
            @Override
            public void onReadEnd() {
                mainViewModel.loadMovies();
            }
        });
        moviesAdapter.setOnClickDetail(new MoviesAdapter.onClickDetail() {
            @Override
            public void onClick(Movie movie) {
                Intent intent=MovieDetailActivity.newIntent(MainActivity.this,movie);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.favourite){
            Intent intent=new Intent(this,FavoritesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        recycleMovie=findViewById(R.id.recycleMovie);
        progressBar=findViewById(R.id.progressBar);
    }
}