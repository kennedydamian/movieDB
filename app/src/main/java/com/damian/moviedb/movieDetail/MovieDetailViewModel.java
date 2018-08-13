package com.damian.moviedb.movieDetail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.damian.moviedb.model.Movie;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<Movie> selectedMovie;

    public MutableLiveData<Movie> getSelectedMovie() {
        if (selectedMovie == null) {
            selectedMovie = new MutableLiveData<Movie>();
        }
        return selectedMovie;
    }
}
