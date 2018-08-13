package com.damian.moviedb.data.repository;

import android.arch.lifecycle.MutableLiveData;

import com.damian.moviedb.data.api.ApiDiscoverData;
import com.damian.moviedb.data.api.MoviesApi;
import com.damian.moviedb.data.api.MoviesApiFactory;
import com.damian.moviedb.model.Movie;
import com.damian.moviedb.util.ModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MoviesRepository {

    private static final String API_KEY = "11c53f5b083e381b1269cac5e70fd434";

    private MoviesApi moviesApi;

    public MoviesRepository() {
        moviesApi = MoviesApiFactory.create();
    }

    public void getMostPopularMovies(MutableLiveData<List<Movie>> data, int page) {

        moviesApi.getMostPopularMovies(page, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiDiscoverData>() {
                    @Override
                    public void onSuccess(ApiDiscoverData response) {
                        data.postValue(ModelMapper.convertApiToUiModel(response.getResults()));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
