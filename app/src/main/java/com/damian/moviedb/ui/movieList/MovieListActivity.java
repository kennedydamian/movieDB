package com.damian.moviedb.ui.movieList;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.damian.moviedb.Constants;
import com.damian.moviedb.R;
import com.damian.moviedb.data.db.model.Movie;
import com.damian.moviedb.ui.MovieBaseActivity;
import com.damian.moviedb.ui.movieDetail.MovieDetailActivity;
import com.damian.moviedb.ui.movieDetail.MovieDetailFragment;
import com.damian.moviedb.ui.movieDetail.MovieDetailViewModel;

public class MovieListActivity extends MovieBaseActivity implements MovieItemNavigator{

    private MovieListViewModel listViewModel;
    private MovieListAdapter listAdapter;
    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        twoPane = findViewById(R.id.fragment_container)!=null;

        listViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        listViewModel.getSelectedMovie().observe(this, movie -> {
            if (movie!=null) {
                openMovieDetails(movie);
            }
        });

        initListAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!twoPane) {
            listViewModel.getSelectedMovie().setValue(null);
        }
    }

    private void initListAdapter() {
        RecyclerView recyclerView = findViewById(R.id.movie_list);
        listAdapter = new MovieListAdapter(listViewModel);

        listViewModel.getMovies().observe(this, listAdapter::submitList);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void openMovieDetails(Movie movie) {
        if (twoPane) {
            MovieDetailViewModel movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
            movieDetailViewModel.getSelectedMovie().setValue(movie);
            addFragmentToContainer(new MovieDetailFragment());
        } else {
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra(Constants.MOVIE_EXTRA, movie);
            startActivity(intent);
        }
    }
}
