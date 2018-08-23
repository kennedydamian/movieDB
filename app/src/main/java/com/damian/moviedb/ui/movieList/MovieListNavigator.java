package com.damian.moviedb.ui.movieList;

import com.damian.moviedb.data.db.model.Movie;

public interface MovieListNavigator {

    void openMovieDetails(Movie selectedMovie);
}
