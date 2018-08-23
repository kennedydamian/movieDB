package com.damian.moviedb.ui.movieList;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.damian.moviedb.Constants;
import com.damian.moviedb.R;
import com.damian.moviedb.data.db.model.Movie;
import com.damian.moviedb.ui.MovieBaseActivity;
import com.damian.moviedb.ui.movieDetail.MovieDetailActivity;

import javax.inject.Inject;

public class MovieListActivity extends MovieBaseActivity implements MovieListNavigator {

    private MovieListViewModel listViewModel;
    private MovieListAdapter listAdapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        listViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MovieListViewModel.class);

        listViewModel.init();
        initListAdapter();
    }

    private void initListAdapter() {
        RecyclerView recyclerView = findViewById(R.id.movie_list);
        int numListCols =  getResources().getInteger(R.integer.list_cols);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numListCols));
        listAdapter = new MovieListAdapter(this);
        listViewModel.getMovies().observe(this, listAdapter::submitList);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void openMovieDetails(Movie selectedMovie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE_EXTRA, selectedMovie);
        startActivity(intent);
    }
}
