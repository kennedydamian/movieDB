package com.damian.moviedb.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.damian.moviedb.data.db.model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
@TypeConverters({DBTypeConverters.class})
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao getMovieDao();
}