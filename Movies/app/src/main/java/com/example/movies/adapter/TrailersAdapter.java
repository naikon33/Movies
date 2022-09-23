package com.example.movies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.model.Trailer;

import java.util.ArrayList;
import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersHolder>{
    private List<Trailer> trailers=new ArrayList<>();
    private onTrailerEndListener onTrailerEndListener;

    public void setOnTrailerEndListener(TrailersAdapter.onTrailerEndListener onTrailerEndListener) {
        this.onTrailerEndListener = onTrailerEndListener;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrailersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(
                R.layout.trailer_item,
                parent,
                false);
        return new TrailersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersHolder holder, int position) {
        Trailer trailer=trailers.get(position);
        holder.tvTrailers.setText(trailer.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTrailerEndListener!=null){
                    onTrailerEndListener.onTrailerEnd(trailer);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    interface onTrailerEndListener{
        void onTrailerEnd(Trailer trailer);
    }

    public class TrailersHolder extends RecyclerView.ViewHolder{
        TextView tvTrailers;
        public TrailersHolder(@NonNull View itemView) {
            super(itemView);
            tvTrailers=itemView.findViewById(R.id.tvTrailers);
        }
    }
}
