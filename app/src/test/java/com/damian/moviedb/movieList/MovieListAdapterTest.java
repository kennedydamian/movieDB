package com.damian.moviedb.movieList;

import android.test.ActivityInstrumentationTestCase2;
import android.view.LayoutInflater;
import android.view.View;

import com.damian.moviedb.R;
import com.damian.moviedb.model.Movie;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieListAdapterTest extends ActivityInstrumentationTestCase2<MovieListActivity> {

    private MovieListAdapter listAdapter;

    public MovieListAdapterTest() {
        super(MovieListActivity.class);

        Movie movie1 = mock(Movie.class);
        when(movie1.getTitle()).thenReturn("Test Movie1");
        Movie movie2 = mock(Movie.class);
        Movie movie3 = mock(Movie.class);

        List<Movie> movies = new ArrayList<Movie>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        MovieListViewModel viewModel = mock(MovieListViewModel.class);

        listAdapter = new MovieListAdapter(movies, viewModel);
    }

    @Test
    public void test_onBindViewHolder() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.movie_list, null, false);
        MovieListViewHolder viewHolder = new MovieListViewHolder(view);
        listAdapter.onBindViewHolder(viewHolder, 0);
        assertEquals("Test Movie1", viewHolder.name.getText());

        assertEquals(View.VISIBLE, viewHolder.movieImageView.getVisibility());
        assertEquals(View.VISIBLE, viewHolder.name.getVisibility());
    }

    @Test
    public void test_getItemCount() {
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

        List<Movie> movies = new ArrayList<Movie>();
        movies.add(movie4);
        movies.add(movie5);

        listAdapter.appendData(movies);
        assertEquals(5, listAdapter.getItemCount());
    }

    @Test
    public void test_appendData_nullList() {
        MovieListViewModel viewModel = mock(MovieListViewModel.class);

        MovieListAdapter emptyListAdapter = new MovieListAdapter(null, viewModel);
        assertEquals(0, emptyListAdapter.getItemCount());
    }
}
