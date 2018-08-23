package com.damian.moviedb.ui.movieList;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damian.moviedb.R;
import com.damian.moviedb.data.db.model.Movie;

public class MovieListAdapter extends PagedListAdapter<Movie, MovieItemViewHolder> {

    private MovieListNavigator movieListNavigator;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Movie selectedMovie = (Movie) view.getTag();
            movieListNavigator.openMovieDetails(selectedMovie);
        }
    };

    protected MovieListAdapter(MovieListNavigator movieListNavigator) {
        super(DIFF_CALLBACK);
        this.movieListNavigator = movieListNavigator;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new MovieItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder holder, int position) {
        Movie movie = getItem(position);
        if (movie!=null && holder!=null) {
            holder.setContent(movie);
            holder.itemView.setTag(movie);
            holder.itemView.setOnClickListener(mOnClickListener);
        }
    }

    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {

        @Override
        public boolean areItemsTheSame(Movie oldMovie, Movie newMovie) {
            return oldMovie.getId() == newMovie.getId();
        }

        @Override
        public boolean areContentsTheSame(Movie oldMovie, Movie newMovie) {
            return oldMovie.equals(newMovie);
        }
    };
}