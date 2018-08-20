package com.damian.moviedb.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ApiDiscoverResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private ArrayList<ApiMovie> results;


    public ArrayList<ApiMovie> getResults() {
        return results;
    }

}
