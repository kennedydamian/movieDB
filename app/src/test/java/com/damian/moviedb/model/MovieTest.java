package com.damian.moviedb.model;

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
public class MovieTest {

    private Parcel mockedParcel = mock(Parcel.class);
    private Movie movie;

    @Before
    public void before() {
        movie = new Movie(123, 9876, true, 1234.567, "TestMovie", 567.123,
                "blah.jpg", "en", "originalTitle", new int[]{123, 345, 567},
                "blah2.jpg", false, "overview", "2018-17-06");
    }

    @Test
    public void getId_returnsCorrectId() {
        assertEquals(123, movie.getId());
    }

    @Test
    public void getVoteCount_returnsCorrectValue() {
        assertEquals(9876, movie.getVoteCount());
    }

    @Test
    public void isVideo_returnsCorrectValue() {
        assertEquals(true, movie.isVideo());
    }

    @Test
    public void getVoteAverage_returnsCorrectValue() {
        assertEquals(1234.567, movie.getVoteAverage(), 0.0);
    }

    @Test
    public void getName_returnsCorrectName() {
        assertEquals("TestMovie", movie.getTitle());
    }

    @Test
    public void getPopularity_returnsCorrectValue() {
        assertEquals(567.123, movie.getPopularity(), 0.0);
    }

    @Test
    public void getPosterPath_returnsCorrectPath() {
        assertEquals("blah.jpg", movie.getPosterPath());
    }

    @Test
    public void getOriginalLanguage_returnsCorrectLanguage() {
        assertEquals("en", movie.getOriginalLanguage());
    }

    @Test
    public void getOriginalTitle_returnsCorrectTitle() {
        assertEquals("originalTitle", movie.getOriginalTitle());
    }

    @Test
    public void getGenreIds_returnsCorrectValues() {
        int[] testArray = {123, 345, 567};
        assertArrayEquals(testArray, movie.getGenreIds());
    }

    @Test
    public void getBackdropPath_returnsCorrectPath() {
        assertEquals("blah2.jpg", movie.getBackdropPath());
    }

    @Test
    public void isAdult_returnsCorrectValue() {
        assertEquals(false, movie.isAdult());
    }

    @Test
    public void getOverview_returnsCorrectOverview() {
        assertEquals("overview", movie.getOverview());
    }

    @Test
    public void getReleaseDate_returnsCorrectDate() {
        assertEquals("2018-17-06", movie.getReleaseDate());
    }

    @Test
    public void parcel_write() {
        movie.writeToParcel(mockedParcel, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
        verify(mockedParcel).writeString("TestMovie");
    }

    @Test
    public void parcel_read() {
        Movie movie = new Movie(mockedParcel);

        movie.writeToParcel(mockedParcel, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);

        verify(mockedParcel, times(7)).readString();
        verify(mockedParcel, times(4)).readInt();
        verify(mockedParcel, times(2)).readDouble();
        verify(mockedParcel, times(1)).createIntArray();
    }

    @Test
    public void getPosterUrl_returnsCorrectUrl() {
        assertEquals("https://image.tmdb.org/t/p/w500/blah.jpg", movie.getPosterUrl());
    }

    @Test
    public void getBackDropUrl_returnsCorrectUrl() {
        assertEquals("https://image.tmdb.org/t/p/w500/blah2.jpg", movie.getBackDropUrl());
    }
}