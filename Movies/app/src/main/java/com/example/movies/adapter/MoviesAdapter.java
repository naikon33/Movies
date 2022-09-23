package com.example.movies.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder>{
    private List<Movie> movies=new ArrayList<>();
    private onReadEndListener readEndListener;
    private onClickDetail onClickDetail;


    public void setOnClickDetail(MoviesAdapter.onClickDetail onClickDetail) {
        this.onClickDetail = onClickDetail;
    }

    public void setReadEndListener(onReadEndListener readEndListener) {
        this.readEndListener = readEndListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false);
        return new MoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHolder holder, int position) {
        Movie movie=movies.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.ivPoster);
        double rating=movie.getRating().getKp();
        int background;
        if (rating>7){
            background=R.drawable.circle_green;
        }
        else if(rating>5){
            background=R.drawable.circle_orange;
        }
        else{
            background=R.drawable.circle_red;
        }
        Drawable drawable= ContextCompat.getDrawable(
                holder.itemView.getContext()
                ,background);
        holder.tvRating.setBackground(drawable);
        holder.tvRating.setText(String.valueOf(rating));
        if (position>=movies.size()-10 && readEndListener!=null){
            readEndListener.onReadEnd();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickDetail!=null){
                    onClickDetail.onClick(movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    interface onReadEndListener{
        void onReadEnd();
    }



    interface onClickDetail{
        void onClick(Movie movie);
    }

    public static class MoviesHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvRating;
        public MoviesHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster=itemView.findViewById(R.id.ivPoster);
            tvRating=itemView.findViewById(R.id.tvRating);
        }
    }
}
