package com.damian.moviedb.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 */
public class DisplayableMovieTest {

    private Parcel mockedParcel = mock(Parcel.class);
    private DisplayableMovie movie;

    @Before
    public void before() {
        movie = new DisplayableMovie(123, 9876, true, 1234.567, "TestMovie", 567.123,
                "blah.jpg", "en", "originalTitle", new int[]{123, 345, 567},
                "blah2.jpg", false, "overview", "2018-17-06");
    }

    @Test
    public void test_getId() {
        assertEquals(123, movie.getId());
    }

    @Test
    public void test_getVoteCount() {
        assertEquals(9876, movie.getVoteCount());
    }

    @Test
    public void test_isVideo() {
        assertEquals(true, movie.isVideo());
    }

    @Test
    public void test_getVoteAverage() {
        assertEquals(1234.567, movie.getVoteAverage(), 0.0);
    }

    @Test
    public void test_getName() {
        assertEquals("TestMovie", movie.getTitle());
    }

    @Test
    public void test_getPopularity() {
        assertEquals(567.123, movie.getPopularity(), 0.0);
    }

    @Test
    public void test_getPosterPath() {
        assertEquals("blah.jpg", movie.getPosterPath());
    }

    @Test
    public void test_getOriginalLanguage() {
        assertEquals("en", movie.getOriginalLanguage());
    }

    @Test
    public void test_getOriginalTitle() {
        assertEquals("originalTitle", movie.getOriginalTitle());
    }

    @Test
    public void test_getGenreIds() {
        int[] testArray = {123, 345, 567};
        assertArrayEquals(testArray, movie.getGenreIds());
    }

    @Test
    public void test_getBackdropPath() {
        assertEquals("blah2.jpg", movie.getBackdropPath());
    }

    @Test
    public void test_isAdult() {
        assertEquals(false, movie.isAdult());
    }

    @Test
    public void test_getOverview() {
        assertEquals("overview", movie.getOverview());
    }

    @Test
    public void test_getReleaseDate() {
        assertEquals("2018-17-06", movie.getReleaseDate());
    }

    @Test
    public void test_parcel_write() {
        movie.writeToParcel(mockedParcel, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
        verify(mockedParcel).writeString("TestMovie");
    }

    @Test
    public void test_parcel_read() {
        DisplayableMovie movie = new DisplayableMovie(mockedParcel);

        movie.writeToParcel(mockedParcel, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);

        verify(mockedParcel, times(7)).readString();
        verify(mockedParcel, times(4)).readInt();
        verify(mockedParcel, times(2)).readDouble();
        verify(mockedParcel, times(1)).createIntArray();
    }

    @Test
    public void test_getPosterUrl() {
        assertEquals("https://image.tmdb.org/t/p/w500/blah.jpg", movie.getPosterUrl());
    }

    @Test
    public void test_getBackDropUrl() {
        assertEquals("https://image.tmdb.org/t/p/w500/blah2.jpg", movie.getBackDropUrl());
    }
}