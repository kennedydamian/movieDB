package com.damian.moviedb.movieList;

import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.damian.moviedb.R;
import com.damian.moviedb.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.movie_image) @VisibleForTesting public ImageView movieImageView;
    @BindView(R.id.movie_name) @VisibleForTesting public TextView name;

    public MovieListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setContent(Movie movie) {
        if(movie.getPosterUrl() != null){

            Picasso.get()
                    .load(movie.getPosterUrl())
                    .resize(300, 600)
                    .centerInside()
                    .into(movieImageView);
        } else {
            movieImageView.setImageBitmap(null);
        }

        name.setText(movie.getTitle());
    }


}
