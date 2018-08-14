package com.damian.moviedb.movieList;

import android.view.LayoutInflater;
import android.view.View;

import com.damian.moviedb.R;
import com.damian.moviedb.model.Movie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MovieListAdapterTest {

    private List<Movie> movies;
    private MovieListViewModel viewModel = mock(MovieListViewModel.class);

    @Before
    public void before() {
        Movie movie1 = mock(Movie.class);
        when(movie1.getTitle()).thenReturn("Test Movie1");
        Movie movie2 = mock(Movie.class);
        Movie movie3 = mock(Movie.class);

        movies = new ArrayList<Movie>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
    }

    @Test
    public void test_getItemCount() {
        MovieListAdapter listAdapter = new MovieListAdapter(movies, viewModel);
        assertEquals(3, listAdapter.getItemCount());
    }

    @Test
    public void test_getItemCount_nullList() {
        MovieListViewModel viewModel = mock(MovieListViewModel.class);

        MovieListAdapter emptyListAdapter = new MovieListAdapter(null, viewModel);
        assertEquals(0, emptyListAdapter.getItemCount());
    }

    @Test
    public void test_appendData() {
        Movie movie4 = mock(Movie.class);
        Movie movie5 = mock(Movie.class);
        List<Movie> extraMovies = new ArrayList<Movie>();
        extraMovies.add(movie4);
        extraMovies.add(movie5);

        MovieListAdapter listAdapter = spy(MovieListAdapter.class);
        listAdapter.setList(movies);
        doNothing().when(listAdapter).onDataChanged();

        listAdapter.appendData(extraMovies);
        assertEquals(5, listAdapter.getItemCount());
        verify(listAdapter, times(1)).onDataChanged();
    }

    @Test
    public void test_appendData_nullList() {
        Movie movie4 = mock(Movie.class);
        Movie movie5 = mock(Movie.class);
        List<Movie> extraMovies = new ArrayList<Movie>();
        extraMovies.add(movie4);
        extraMovies.add(movie5);

        MovieListAdapter listAdapter = spy(MovieListAdapter.class);
        doNothing().when(listAdapter).onDataChanged();

        listAdapter.appendData(extraMovies);
        assertEquals(2, listAdapter.getItemCount());
        verify(listAdapter, times(1)).onDataChanged();
    }
}
