package com.damian.moviedb.ui.movieList;

import org.junit.Before;
import org.junit.Test;

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

    private List<DisplayableMovie> movies;
    private MovieListViewModel viewModel = mock(MovieListViewModel.class);

    @Before
    public void before() {
        DisplayableMovie movie1 = mock(DisplayableMovie.class);
        when(movie1.getTitle()).thenReturn("Test Movie1");
        DisplayableMovie movie2 = mock(DisplayableMovie.class);
        DisplayableMovie movie3 = mock(DisplayableMovie.class);

        movies = new ArrayList<DisplayableMovie>();
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
        DisplayableMovie movie4 = mock(DisplayableMovie.class);
        DisplayableMovie movie5 = mock(DisplayableMovie.class);
        List<DisplayableMovie> extraMovies = new ArrayList<DisplayableMovie>();
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
        DisplayableMovie movie4 = mock(DisplayableMovie.class);
        DisplayableMovie movie5 = mock(DisplayableMovie.class);
        List<DisplayableMovie> extraMovies = new ArrayList<DisplayableMovie>();
        extraMovies.add(movie4);
        extraMovies.add(movie5);

        MovieListAdapter listAdapter = spy(MovieListAdapter.class);
        doNothing().when(listAdapter).onDataChanged();

        listAdapter.appendData(extraMovies);
        assertEquals(2, listAdapter.getItemCount());
        verify(listAdapter, times(1)).onDataChanged();
    }
}
