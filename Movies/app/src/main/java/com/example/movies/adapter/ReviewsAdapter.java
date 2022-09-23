package com.example.movies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsHolder>{
    private List<Review> reviews=new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false);
        return new ReviewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsHolder holder, int position) {
        Review review= reviews.get(position);
        String status=review.getStatus();
        int type;
        if (status.equals("Позитивный")){
            type= android.R.color.holo_green_light;
        }
        else if (status.equals("Нейтральный")){
            type= android.R.color.holo_orange_light;
        }
        else {
            type= android.R.color.holo_red_light;
        }
        int contextCompat=ContextCompat.getColor(holder.itemView.getContext(),type);
        holder.constraint.setBackgroundColor(contextCompat);
        holder.tvAuthor.setText(review.getAuthor());
        holder.tvReview.setText(review.getReview());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewsHolder extends RecyclerView.ViewHolder{
        ConstraintLayout constraint;
        TextView tvAuthor;
        TextView tvReview;

        public ReviewsHolder(@NonNull View itemView) {
            super(itemView);
            constraint=itemView.findViewById(R.id.constraint);
            tvAuthor=itemView.findViewById(R.id.tvAuthor);
            tvReview=itemView.findViewById(R.id.tvReview);
        }
    }
}
