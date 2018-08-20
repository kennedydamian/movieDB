package com.damian.moviedb.util;

import com.damian.moviedb.data.api.model.ApiMovie;
import com.damian.moviedb.data.db.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class ModelMapper {

    public static List<Movie> convertApiToDbModel(List<ApiMovie> apiMovies, int resultPage) {
        List<Movie> movieEntities = new ArrayList<>();
        for (ApiMovie apiMovieData: apiMovies) {

            movieEntities.add(new Movie(apiMovieData.getId(),
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
                    apiMovieData.getReleaseDate(),
                    resultPage));
        }
        return movieEntities;
    }
}
