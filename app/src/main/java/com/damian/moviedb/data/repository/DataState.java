package com.damian.moviedb.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.damian.moviedb.data.db.model.Movie;

public class DataState {

    public LiveData<PagedList<Movie>> pagedList;

    public DataState (LiveData<PagedList<Movie>> pagedList) {
        this.pagedList = pagedList;
    }
}
