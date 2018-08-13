package com.damian.moviedb.data.api;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET("discover/movie")
    Single<ApiDiscoverData> getMostPopularMovies(@Query("page") int page,
                                               @Query("api_key") String apiKey);
}
