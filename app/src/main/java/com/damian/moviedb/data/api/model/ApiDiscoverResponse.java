package com.damian.moviedb.data.api.model;

import android.support.annotation.VisibleForTesting;

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

    public int getTotalResults() {
        return totalResults;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public ApiDiscoverResponse(int page, int totalResults, int totalPages, ArrayList<ApiMovie> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

}
