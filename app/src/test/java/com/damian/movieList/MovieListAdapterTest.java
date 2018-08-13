package com.damian.movieList;

import android.test.ActivityInstrumentationTestCase2;
import android.view.LayoutInflater;
import android.view.View;

import com.damian.moviedb.R;
import com.damian.moviedb.model.Movie;
import com.damian.moviedb.movieList.MovieListActivity;
import com.damian.moviedb.movieList.MovieListAdapter;
import com.damian.moviedb.movieList.MovieListViewHolder;
import com.damian.moviedb.movieList.MovieListViewModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieListAdapterTest extends ActivityInstrumentationTestCase2<MovieListActivity> {

    private MovieListAdapter mAdapter;

    public MovieListAdapterTest() {
        super(MovieListActivity.class);

        Movie movie1 = mock(Movie.class);
        when(movie1.getTitle()).thenReturn("Test Movie1");
        Movie movie2 = mock(Movie.class); when(movie2.getTitle()).thenReturn("Test Movie2");
        Movie movie3 = mock(Movie.class);

        List<Movie> movies = new ArrayList<Movie>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        MovieListViewModel viewModel = mock(MovieListViewModel.class);

        mAdapter = new MovieListAdapter(movies, viewModel);
    }

    @Test
    public void test_onBindViewHolder() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.movie_list, null, false);
        MovieListViewHolder viewHolder = new MovieListViewHolder(view);
        mAdapter.onBindViewHolder(viewHolder, 0);
        assertEquals("Test Movie1", viewHolder.name.getText());


        assertEquals(View.VISIBLE, viewHolder.movieImageView);
        assertEquals(View.VISIBLE, viewHolder.name);
    }

    @Test
    public void test_getItemCount() {
        assertEquals(3, mAdapter.getItemCount());
    }
}
