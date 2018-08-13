package com.damian.moviedb.movieList;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.damian.moviedb.data.repository.MoviesRepository;
import com.damian.moviedb.model.Movie;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movies;
    private MutableLiveData<Movie> selectedMovie;
    private MoviesRepository moviesRepo;

    public MovieListViewModel() {
        moviesRepo = new MoviesRepository();
    }

    public LiveData<List<Movie>> getMovies() {
        if (movies == null) {
            movies = new MutableLiveData<List<Movie>>();
        }
        return movies;
    }

    public MutableLiveData<Movie> getSelectedMovie() {
        if (selectedMovie == null) {
            selectedMovie = new MutableLiveData<Movie>();
        }
        return selectedMovie;
    }

    public void getPopularMovies(int page) {
        moviesRepo.getMostPopularMovies(movies, page);
    }

}
