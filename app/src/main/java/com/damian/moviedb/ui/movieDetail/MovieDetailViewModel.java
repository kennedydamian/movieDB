package com.damian.moviedb.ui.movieDetail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.damian.moviedb.data.db.model.Movie;

import javax.inject.Inject;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<Movie> selectedMovie;

    @Inject
    public MovieDetailViewModel() {}

    public MutableLiveData<Movie> getSelectedMovie() {
        if (selectedMovie == null) {
            selectedMovie = new MutableLiveData<Movie>();
        }
        return selectedMovie;
    }
}
