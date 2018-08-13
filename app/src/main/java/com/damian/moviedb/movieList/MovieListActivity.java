package com.damian.moviedb.movieList;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.damian.moviedb.Constants;
import com.damian.moviedb.MovieBaseActivity;
import com.damian.moviedb.R;
import com.damian.moviedb.model.Movie;
import com.damian.moviedb.movieDetail.MovieDetailActivity;
import com.damian.moviedb.movieDetail.MovieDetailFragment;
import com.damian.moviedb.movieDetail.MovieDetailViewModel;

import java.util.ArrayList;

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
        listViewModel.getMovies().observe(this, movies -> {
            listAdapter.appendData(movies);
        });
        listViewModel.getSelectedMovie().observe(this, movie -> {
            if (movie!=null) {
                openMovieDetails(movie);
            }
        });

        initListAdapter();

        listViewModel.getPopularMovies(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!twoPane) {
            listViewModel.getSelectedMovie().setValue(null);
        }
    }

    private void initListAdapter() {
        RecyclerView recyclerView = findViewById(R.id.route_list);

        recyclerView.addOnScrollListener(new InfiniteScrollListener() {
            @Override
            public void onEndReached(int page) {
                listViewModel.getPopularMovies(page);
            }
        });
        listAdapter = new MovieListAdapter(new ArrayList<Movie>(), listViewModel);
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
