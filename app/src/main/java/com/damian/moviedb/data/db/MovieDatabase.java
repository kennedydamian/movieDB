package com.damian.moviedb.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {MovieEntity.class}, version = 1)
@TypeConverters({DBTypeConverters.class})
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
}
