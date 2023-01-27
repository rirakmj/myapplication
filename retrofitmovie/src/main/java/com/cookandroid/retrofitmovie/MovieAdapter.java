package com.cookandroid.retrofitmovie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList;
    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        protected TextView txId, txName, txAge, txIntro;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txId=(TextView) itemView.findViewById(R.id.txId);
            this.txName=(TextView) itemView.findViewById(R.id.txName);
            this.txAge=(TextView) itemView.findViewById(R.id.txAge);
            this.txIntro=(TextView) itemView.findViewById(R.id.txIntro);
        }
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.txId.setText(Integer.toString(movie.getId()));
        holder.txName.setText(movie.getName());
        holder.txAge.setText(Integer.toString(movie.getAge()));
        holder.txIntro.setText(movie.getIntro());
    }

    @Override
    public int getItemCount() {
        return (null != movieList ? movieList.size() : 0);
    }
}
