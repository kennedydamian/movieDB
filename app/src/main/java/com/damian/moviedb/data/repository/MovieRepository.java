package com.damian.moviedb.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.damian.moviedb.Constants;
import com.damian.moviedb.data.api.MovieApi;
import com.damian.moviedb.data.api.MovieApiCreator;
import com.damian.moviedb.data.api.model.ApiDiscoverResponse;
import com.damian.moviedb.data.db.MovieDatabase;
import com.damian.moviedb.data.db.model.Movie;
import com.damian.moviedb.util.ModelMapper;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieRepository implements Repository {

    private MovieApi moviesApi;
    private MovieDatabase movieDb;

    private MutableLiveData<DataState> dataState = new MutableLiveData<>();

    private static final int LIST_PAGE_SIZE                 = 20;
    private static final int LIST_INITIAL_LOAD_SIZE_HINT    = 20;
    private static final int LIST_PREFETCH_DISTANCE         = 5;

    public MovieRepository(Application application) {
        moviesApi = MovieApiCreator.create();
        movieDb = MovieDatabase.getInstance(application);
    }

    public LiveData<DataState> getDataState() {
        if (dataState==null) {
            dataState = new MutableLiveData<>();
        }
        return dataState;
    }

    public void initRepo() {
        MovieBoundaryCallback boundaryCallback =
                new MovieBoundaryCallback(this);

        DataSource.Factory dataSourceFactory = movieDb.getMovieDao().moviesByPopularity();

        PagedList.Config.Builder listConfigBuilder = new PagedList.Config.Builder();
        listConfigBuilder.setInitialLoadSizeHint(LIST_INITIAL_LOAD_SIZE_HINT);
        listConfigBuilder.setPageSize(LIST_PAGE_SIZE);
        listConfigBuilder.setPrefetchDistance(LIST_PREFETCH_DISTANCE);

        LivePagedListBuilder listBuilder =
                new LivePagedListBuilder(dataSourceFactory, listConfigBuilder.build())
                .setBoundaryCallback(boundaryCallback);

        dataState.setValue(new DataState(listBuilder.build()));
    }

    public void insertItemsIntoDb(List<Movie> movies) {
        movieDb.getMovieDao().insert(movies);
    }

    @Override
    public void fetchPageOfData(int page) {
        moviesApi.getMostPopularMovies(page, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<ApiDiscoverResponse>() {
                    @Override
                    public void onSuccess(ApiDiscoverResponse response) {
                        List<Movie> movieEntities = ModelMapper.convertApiToDbModel(response.getResults(), page);
                        insertItemsIntoDb(movieEntities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Handle errors
                    }
                });
    }
}
