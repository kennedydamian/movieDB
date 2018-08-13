package com.damian.moviedb.movieList;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private boolean loading = true;
    private int oldTotalItemCount = 0;
    private int page = 1;


    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) view.getLayoutManager();

        int totalItemCount = layoutManager.getItemCount();
        if (totalItemCount==0) {
            return;
        }
        if (totalItemCount > oldTotalItemCount) {
            loading = false;
        }

        int lastItem = layoutManager.findLastVisibleItemPosition();
        if (lastItem + 5 >= totalItemCount && !loading) {
            oldTotalItemCount = totalItemCount;
            loading = true;
            page++;
            onEndReached(page);
        }
    }

    public abstract void onEndReached(int page);
}