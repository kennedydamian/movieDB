package com.damian.moviedb.data.repository;

import com.damian.moviedb.data.db.model.Movie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class MovieBoundaryCallbackTest {

    private MovieBoundaryCallback boundaryCallback;
    private MovieRepository repo;

    @Before
    public void before() {
        repo = Mockito.mock(MovieRepository.class);
        boundaryCallback = new MovieBoundaryCallback(repo);
    }

    @Test
    public void test_onZeroItemsLoaded_fetchFirstPage () {
        boundaryCallback.onZeroItemsLoaded();

        verify(repo).fetchPageOfData(1);
    }

    @Test
    public void test_onItemAtEndLoaded_fetchNextPage () {
        Movie movie = Mockito.mock(Movie.class);
        Mockito.when(movie.getResultPage()).thenReturn(3);
        boundaryCallback.onItemAtEndLoaded(movie);

        verify(repo).fetchPageOfData(4);
    }
}
