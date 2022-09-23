package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movies.adapter.ReviewsAdapter;
import com.example.movies.adapter.TrailersAdapter;
import com.example.movies.model.Movie;
import com.example.movies.model.Review;
import com.example.movies.model.Trailer;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView ivPosterDetail;
    private ImageView ivStar;

    private TextView tvName;
    private TextView tvYear;
    private TextView tvDescription;

    private DetailViewModel detailViewModel;

    private static final String TAG="movie";

    private TrailersAdapter trailersAdapter;
    private RecyclerView recycleDetail;

    private RecyclerView recycleReview;
    private ReviewsAdapter reviewsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initViews();
        trailersAdapter=new TrailersAdapter();
        reviewsAdapter=new ReviewsAdapter();
        detailViewModel=new ViewModelProvider(this).get(DetailViewModel.class);
        Movie movie=(Movie) getIntent().getSerializableExtra(TAG);
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(ivPosterDetail);
        tvName.setText(movie.getName());
        tvYear.setText(String.valueOf(movie.getYear()));
        tvDescription.setText(movie.getDescription());
        detailViewModel.loadTrailer(movie.getId());
        detailViewModel.getIsTrailer().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailersAdapter.setTrailers(trailers);
            }
        });
        recycleDetail.setAdapter(trailersAdapter);
        trailersAdapter.setOnTrailerEndListener(new TrailersAdapter.onTrailerEndListener() {
            @Override
            public void onTrailerEnd(Trailer trailer) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });
        detailViewModel.loadReview(movie.getId());
        recycleReview.setAdapter(reviewsAdapter);
        detailViewModel.getIsReview().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewsAdapter.setReviews(reviews);
            }
        });
        Drawable starOff= ContextCompat.getDrawable(this,android.R.drawable.star_big_off);
        Drawable starOn=ContextCompat.getDrawable(this, android.R.drawable.star_big_on);
        detailViewModel.getFavouriteMovies(movie.getId()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieItem) {
                if (movieItem==null){
                    ivStar.setImageDrawable(starOff);
                    ivStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            detailViewModel.loadAdd(movie);
                        }
                    });
                }
                else{
                    ivStar.setImageDrawable(starOn);
                    ivStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            detailViewModel.loadRemove(movie.getId());
                        }
                    });
                }
            }
        });
    }

    public static Intent newIntent(Context context,Movie movie){
        Intent intent=new Intent(context,MovieDetailActivity.class);
        intent.putExtra(TAG,movie);
        return intent;
    }

    private void initViews() {
        ivPosterDetail=findViewById(R.id.ivPosterDetail);
        tvName=findViewById(R.id.tvName);
        tvYear=findViewById(R.id.tvYear);
        tvDescription=findViewById(R.id.tvDescription);
        recycleDetail=findViewById(R.id.recycleDetail);
        recycleReview=findViewById(R.id.recycleReview);
        ivStar=findViewById(R.id.ivStar);
    }
}