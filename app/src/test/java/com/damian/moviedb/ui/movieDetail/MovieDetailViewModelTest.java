package com.damian.moviedb.ui.movieDetail;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.damian.moviedb.data.db.model.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MovieDetailViewModelTest {

    private MovieDetailViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void before() {
        viewModel = new MovieDetailViewModel();
    }


    @Test
    public void test_selectedMovie_observerNotified_onChange() {
        Observer<Movie> observer = mock(Observer.class);
        viewModel.getSelectedMovie().observeForever(observer);
        Movie mockMovie = mock(Movie.class);

        viewModel.getSelectedMovie().postValue(mockMovie);

        verify(observer, times(1)).onChanged(mockMovie);
    }
}
