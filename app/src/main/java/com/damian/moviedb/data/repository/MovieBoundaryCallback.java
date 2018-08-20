package com.damian.moviedb.data.repository;

import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.damian.moviedb.data.db.model.Movie;

public class MovieBoundaryCallback extends PagedList.BoundaryCallback<Movie> {

    private Repository repo;

    public MovieBoundaryCallback(Repository repo) {
        this.repo = repo;
    }

    @Override
    public void onZeroItemsLoaded() {
        repo.fetchPageOfData(1);
    }

    @Override
    public void onItemAtEndLoaded(@NonNull Movie itemAtEnd) {
        repo.fetchPageOfData(itemAtEnd.getResultPage()+1);
    }
}