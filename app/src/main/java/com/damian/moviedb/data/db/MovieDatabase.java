package com.damian.moviedb.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.damian.moviedb.data.db.model.Movie;

@Database(entities = {Movie.class}, version = 1)
@TypeConverters({DBTypeConverters.class})
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance;
    private static final String DB_NAME= "movies";

    public abstract MovieDao getMovieDao();

    public static MovieDatabase getInstance(Context context) {
        if (instance!=null) {
            return instance;
        }
        instance = Room.databaseBuilder(context, MovieDatabase.class, DB_NAME).build();

        return instance;
    }
}