package com.damian.moviedb.data.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.damian.moviedb.data.db.model.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MovieDaoTest {
    private MovieDao dao;
    private MovieDatabase db;
    private Movie movie;
    private Movie movie2;

    @Before
    public void before() {
        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase.class).build();
        dao = db.getMovieDao();

        movie = new Movie(123, 9876, true, 1234.567, "TestMovie", 567.123,
                "blah.jpg", "en", "originalTitle", new int[]{123, 345, 567},
                "blah2.jpg", false, "overview", "2018-17-06", 1);
        movie2 = new Movie(321, 9876, true, 1234.567, "TestMovie", 567.123,
                "blah.jpg", "en", "originalTitle", new int[]{123, 345, 567},
                "blah2.jpg", false, "overview", "2018-17-06", 1);
    }

    @After
    public void after() {
        db.close();
    }

    @Test
    public void test_insertMovies() {
        List<Movie> moviesToInsert = new ArrayList<>();
        moviesToInsert.add(movie);
        moviesToInsert.add(movie2);

        dao.insert(moviesToInsert);
        List<Movie> retrievedMovies = dao.allMovies();

        assertEquals(2, retrievedMovies.size());

        assertEquals(movie.getId(), retrievedMovies.get(0).getId());
    }
}