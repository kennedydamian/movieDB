package com.damian.moviedb.ui.movieList;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.support.annotation.VisibleForTesting;

import com.damian.moviedb.data.db.model.Movie;
import com.damian.moviedb.data.repository.DataState;
import com.damian.moviedb.data.repository.MovieRepository;

import javax.inject.Inject;

public class MovieListViewModel extends ViewModel {

    @Inject
    MovieRepository moviesRepo;

    private LiveData<DataState> repoResult;
    private LiveData<PagedList<Movie>> movies;

    @Inject
    public MovieListViewModel(MovieRepository moviesRepo) {
        this.moviesRepo = moviesRepo;
    }

    public void init() {
        repoResult = moviesRepo.getDataState();
        movies = Transformations.switchMap(repoResult, result -> {
            return result.pagedList;
        });

        moviesRepo.initRepo();
    }

    public LiveData<PagedList<Movie>> getMovies() {
        return movies;
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public LiveData<DataState> getRepoResult() {
        return repoResult;
    }


}
