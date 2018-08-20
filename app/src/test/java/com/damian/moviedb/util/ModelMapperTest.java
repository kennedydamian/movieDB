package com.damian.moviedb.util;

import com.damian.moviedb.data.api.model.ApiMovie;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ModelMapperTest {

    private DisplayableMovie movie;
    private ApiMovie apiMovieData;

    @Before
    public void before() {
        movie = new DisplayableMovie(123, 9876, true, 1234.567, "TestMovie", 567.123,
                "blah.jpg", "en", "originalTitle", new int[]{123, 345, 567},
                "blah2.jpg", false, "overview", "2018-17-06");

        apiMovieData = new ApiMovie(123, 9876, true, 1234.567, "TestMovie", 567.123,
                "blah.jpg", "en", "originalTitle", new int[]{123, 345, 567},
                "blah2.jpg", false, "overview", "2018-17-06");
    }

    @Test
    public void test_convertApiToUiModel() {
        List<ApiMovie> apiList = new ArrayList<>();
        apiList.add(apiMovieData);

        DisplayableMovie convertedMovie = ModelMapper.convertApiToUiModel(apiList).get(0);
        assertEquals(movie.getId(), convertedMovie.getId());
        assertEquals(movie.getTitle(), convertedMovie.getTitle());
        assertEquals(movie.getOriginalLanguage(), convertedMovie.getOriginalLanguage());
        assertEquals(movie.getOriginalTitle(), convertedMovie.getOriginalTitle());
        assertArrayEquals(movie.getGenreIds(), convertedMovie.getGenreIds());
        assertEquals(movie.getBackdropPath(), convertedMovie.getBackdropPath());
        assertEquals(movie.getPosterUrl(), convertedMovie.getPosterUrl());
    }

}
