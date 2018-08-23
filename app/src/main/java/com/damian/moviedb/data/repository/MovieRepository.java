package com.damian.moviedb.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.damian.moviedb.Constants;
import com.damian.moviedb.data.api.MovieApi;
import com.damian.moviedb.data.api.model.ApiDiscoverResponse;
import com.damian.moviedb.data.db.MovieDatabase;
import com.damian.moviedb.data.db.model.Movie;
import com.damian.moviedb.util.ModelMapper;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieRepository implements Repository {

    private MovieApi moviesApi;
    private MovieDatabase movieDb;
    private Executor executor;

    private MutableLiveData<DataState> dataState = new MutableLiveData<>();

    private static final int LIST_PAGE_SIZE                 = 5;
    private static final int LIST_INITIAL_LOAD_SIZE_HINT    = 5;
    private static final int LIST_PREFETCH_DISTANCE         = 1;


    @Inject
    public MovieRepository(MovieApi moviesApi, MovieDatabase movieDb, Executor executor) {
        this.moviesApi = moviesApi;
        this.movieDb = movieDb;
        this.executor = executor;
    }

    public LiveData<DataState> getDataState() {
        if (dataState==null) {
            dataState = new MutableLiveData<>();
        }

        return dataState;
    }

    @SuppressWarnings("unchecked")
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

    public void insertMoviesIntoDb(List<Movie> movies) {
        movieDb.getMovieDao().insert(movies);

        executor.execute(() -> {
            movieDb.runInTransaction(() -> {
                movieDb.getMovieDao().insert(movies);
            });
        });
    }

    @Override
    public void fetchPageOfData(int page) {
        DisposableSingleObserver<ApiDiscoverResponse> disposable = getApiObserver(page);

        moviesApi.getMostPopularMovies(page, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .subscribeWith(disposable);

    }

    private DisposableSingleObserver getApiObserver(int page) {
        return new DisposableSingleObserver<ApiDiscoverResponse>() {
            @Override
            public void onSuccess(ApiDiscoverResponse response) {
                if (response.getTotalResults()>0 && response.getResults()!=null) {
                    List<Movie> movieEntities = ModelMapper.convertApiToDbModel(response.getResults(), page);
                    insertMoviesIntoDb(movieEntities);
                }
            }

            @Override
            public void onError(Throwable e) {
                // todo
                // handle api Errors here
                // Set error field on observed dataState
            }
        };
    }
}
