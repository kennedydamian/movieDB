package com.damian.moviedb.ui.movieDetail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MovieDetailModule {

    @ContributesAndroidInjector
    abstract MovieDetailFragment movieDetailFragment();

}