package com.damian.moviedb.di;

import com.damian.moviedb.ui.movieDetail.MovieDetailActivity;
import com.damian.moviedb.ui.movieDetail.MovieDetailModule;
import com.damian.moviedb.ui.movieList.MovieListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MovieListActivity contributeMovieListActivity();

    @ContributesAndroidInjector(modules = MovieDetailModule.class)
    abstract MovieDetailActivity contributeMovieDetailActivity();

}