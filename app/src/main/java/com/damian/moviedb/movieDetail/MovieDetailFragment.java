package com.damian.moviedb.movieDetail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.damian.moviedb.R;
import com.damian.moviedb.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailFragment extends Fragment {

    @BindView(R.id.poster_image) ImageView backdrop;
    @BindView(R.id.release) TextView release;
    @BindView(R.id.rating) TextView rating;
    @BindView(R.id.adult) TextView adult;
    @BindView(R.id.language) TextView language;
    @BindView(R.id.overview) TextView overview;
    @BindView(R.id.title) TextView title;

    private MovieDetailViewModel detailViewModel;

    public MovieDetailFragment () {}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_detail_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        detailViewModel = ViewModelProviders.of(getActivity()).get(MovieDetailViewModel.class);
        detailViewModel.getSelectedMovie().observe(getActivity(), movie -> {
            populateView(movie);
        });
    }

    private void populateView(Movie movie) {
        if (movie == null) {
            return;
        }

        Picasso.get()
                .load(movie.getPosterUrl())
                .resize(400, 800)
                .centerInside()
                .into(backdrop);

        release.setText(movie.getReleaseDate());
        rating.setText(String.valueOf(movie.getVoteAverage()));
        adult.setText((movie.isAdult()?R.string.yes:R.string.no));
        language.setText(String.valueOf(movie.getOriginalLanguage()));
        overview.setText(movie.getOverview());
        title.setText(movie.getTitle());
    }
}
