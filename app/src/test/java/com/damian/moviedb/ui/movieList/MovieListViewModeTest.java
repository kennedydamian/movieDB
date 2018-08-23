package com.damian.moviedb.ui.movieList;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;

import com.damian.moviedb.data.db.model.Movie;
import com.damian.moviedb.data.repository.DataState;
import com.damian.moviedb.data.repository.MovieRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MovieListViewModeTest {

    private MovieRepository repo;

    private MovieListViewModel viewModel;
    private MutableLiveData<PagedList<Movie>> pagedList;
    private MutableLiveData<DataState> liveDataState;
    private DataState dataState;
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void before() {
        repo = mock(MovieRepository.class);
        viewModel = new MovieListViewModel(repo);

        liveDataState = new MutableLiveData<DataState>();
        pagedList = new MutableLiveData<>();
        dataState = new DataState(pagedList);

        liveDataState.postValue(dataState);

        when(repo.getDataState()).thenReturn(liveDataState);
    }

    @Test
    public void test_repoResult_observerNotified_onChange() {
        Observer<DataState> observer = mock(Observer.class);
        viewModel.init();

        viewModel.getRepoResult().observeForever(observer);

        verify(observer, times(1)).onChanged(any());
    }

    @Test
    public void test_movies_observerNotified_onChange() {
        Observer<PagedList<Movie>> observer = mock(Observer.class);
        viewModel.init();

        viewModel.getMovies().observeForever(observer);
        pagedList.postValue(mock(PagedList.class));

        verify(observer, times(1)).onChanged(any());
    }


}
