package com.damian.moviedb.data.api;

import com.damian.moviedb.data.api.model.ApiDiscoverResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("discover/movie")
    Single<ApiDiscoverResponse> getMostPopularMovies(@Query("page") int page,
                                                     @Query("api_key") String apiKey);
}
