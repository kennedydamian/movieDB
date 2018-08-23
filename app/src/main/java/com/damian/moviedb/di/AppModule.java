package com.damian.moviedb.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.damian.moviedb.data.api.MovieApi;
import com.damian.moviedb.data.db.MovieDatabase;
import com.damian.moviedb.data.repository.MovieRepository;
import com.damian.moviedb.ui.MovieViewModelFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {
    @Binds
    abstract Context bindContext(Application application);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(MovieViewModelFactory factory);

    @Singleton
    @Provides
    static MovieApi provideMovieApi() {
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi.class);
    }

    @Singleton
    @Provides
    static MovieDatabase provideDb(Application app) {
        return Room.databaseBuilder(app, MovieDatabase.class, "movies").build();
    }

    @Singleton
    @Provides
    static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    static MovieRepository provideMovieRepository(MovieApi moviesApi, MovieDatabase movieDb, Executor executor) {
        return new MovieRepository(moviesApi, movieDb, executor);
    }
}