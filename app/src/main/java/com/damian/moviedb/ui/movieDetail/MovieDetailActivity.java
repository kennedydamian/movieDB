package com.damian.moviedb.ui.movieDetail;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.damian.moviedb.Constants;
import com.damian.moviedb.R;
import com.damian.moviedb.data.db.model.Movie;
import com.damian.moviedb.ui.MovieBaseActivity;

import javax.inject.Inject;

public class MovieDetailActivity extends MovieBaseActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_detail);
        MovieDetailViewModel detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel.class);

        Movie Movie = this.getIntent().getParcelableExtra(Constants.MOVIE_EXTRA);
        detailViewModel.getSelectedMovie().setValue(Movie);

        addFragmentToContainer(new MovieDetailFragment());
    }
}
