package com.damian.moviedb.data.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ApiDiscoverData {

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private ArrayList<ApiMovieData> results;


    public ArrayList<ApiMovieData> getResults() {
        return results;
    }

}
