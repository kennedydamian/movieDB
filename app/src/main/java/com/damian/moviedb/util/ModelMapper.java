package com.damian.moviedb.util;

import com.damian.moviedb.data.api.ApiMovieData;
import com.damian.moviedb.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class ModelMapper {

    public static List<Movie> convertApiToUiModel(List<ApiMovieData> apiMovie) {
        List<Movie> displayableMovies = new ArrayList<Movie>();
        for (ApiMovieData apiMovieData: apiMovie) {

            displayableMovies.add(new Movie(apiMovieData.getId(),
                    apiMovieData.getVoteCount(),
                    apiMovieData.isVideo(),
                    apiMovieData.getVoteAverage(),
                    apiMovieData.getTitle(),
                    apiMovieData.getPopularity(),
                    apiMovieData.getPosterPath(),
                    apiMovieData.getOriginalLanguage(),
                    apiMovieData.getOriginalTitle(),
                    apiMovieData.getGenreIds(),
                    apiMovieData.getBackdropPath(),
                    apiMovieData.isAdult(),
                    apiMovieData.getOverview(),
                    apiMovieData.getReleaseDate()));
        }
        return displayableMovies;
    }
}
