package com.damian.moviedb.data.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.paging.DataSource;

import com.damian.moviedb.Constants;
import com.damian.moviedb.data.api.MovieApi;
import com.damian.moviedb.data.api.model.ApiDiscoverResponse;
import com.damian.moviedb.data.db.MovieDao;
import com.damian.moviedb.data.db.MovieDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.Executor;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class MovieRepositoryTest {

    private MovieApi mockApi;;
    private MovieDatabase db;;
    private MovieDao dao;
    private DataSource.Factory factory;
    private Executor executor;
    private MovieRepository repo;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void before() {
        mockApi = Mockito.mock(MovieApi.class);
        db = Mockito.mock(MovieDatabase.class);
        dao = Mockito.mock(MovieDao.class);
        factory = Mockito.mock(DataSource.Factory.class);
        executor = Mockito.mock(Executor.class);

        when(db.getMovieDao()).thenReturn(dao);
        when(dao.moviesByPopularity()).thenReturn(factory);

        RxJavaPlugins.setIoSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Test
    public void test_initRepo_createsPagedList() {
        repo = new MovieRepository(mockApi, db, executor);

        DataState initialState = repo.getDataState().getValue();
        assert(initialState==null);

        repo.initRepo();
        DataState state = repo.getDataState().getValue();

        assert(state.pagedList!=null);
    }

    @Test
    public void test_fetchPageOfData_emptyResponse() {
        MovieDao spyDao = spy(MovieDao.class);
        when(db.getMovieDao()).thenReturn(spyDao);
        repo = new MovieRepository(mockApi, db, executor);

        ApiDiscoverResponse apiResponse = new ApiDiscoverResponse(0,0,0,null);

        when(mockApi.getMostPopularMovies(3, Constants.API_KEY)).thenReturn(Single.just(apiResponse));
        repo.fetchPageOfData(3);
        verify(spyDao, never()).insert(anyList());
    }

    @Test
    public void test_fetchPageOfData_emptyResults() {
        MovieDao spyDao = spy(MovieDao.class);
        when(db.getMovieDao()).thenReturn(spyDao);
        repo = new MovieRepository(mockApi, db, executor);

        ApiDiscoverResponse apiResponse = new ApiDiscoverResponse(3,12345,324,null);

        when(mockApi.getMostPopularMovies(3, Constants.API_KEY)).thenReturn(Single.just(apiResponse));
        repo.fetchPageOfData(3);

        verify(spyDao, never()).insert(anyList());
    }
}
