package com.damian.moviedb.data.db;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.damian.moviedb.data.db.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM Movie ORDER BY popularity DESC")
    DataSource.Factory<Integer, Movie> moviesByPopularity();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Movie> movies);
}