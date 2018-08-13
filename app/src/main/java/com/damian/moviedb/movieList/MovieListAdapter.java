package com.damian.moviedb.movieList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damian.moviedb.R;
import com.damian.moviedb.model.Movie;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListViewHolder> {

    private List<Movie> movies;
    private MovieListViewModel viewModel;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Movie selectedMovie = (Movie) view.getTag();
            viewModel.getSelectedMovie().setValue(selectedMovie);
        }
    };

    MovieListAdapter(List<Movie> movies, MovieListViewModel viewModel) {

        this.viewModel =  viewModel;
        this.movies = movies;
    }

    public void appendData (List<Movie> updatedMovies) {
        movies.addAll(updatedMovies);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movies==null ? null:movies.size();
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder holder, int position) {
        Movie movie = movies.get(position);
        if (movie != null) {
            holder.setContent(movie);
        }
        holder.itemView.setTag(movie);
        holder.itemView.setOnClickListener(mOnClickListener);
    }
}