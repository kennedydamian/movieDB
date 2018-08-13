package com.damian.moviedb.movieDetail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.damian.moviedb.Constants;
import com.damian.moviedb.MovieBaseActivity;
import com.damian.moviedb.R;
import com.damian.moviedb.model.Movie;

public class MovieDetailActivity extends MovieBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_detail);

        MovieDetailViewModel detailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        Movie selectedMovie = this.getIntent().getParcelableExtra(Constants.MOVIE_EXTRA);
        detailViewModel.getSelectedMovie().setValue(selectedMovie);

        addFragmentToContainer(new MovieDetailFragment());
    }
}
