package com.base.mvvm.common.view.widget;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.base.mvvm.common.view.callback.SwipeRefreshCallback;


public class SwipeRefreshHelper {
    private static final int DELAY = 500;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SwipeRefreshCallback mSwipeRefreshCallback;

    public SwipeRefreshHelper(@NonNull SwipeRefreshLayout mSwipeRefreshLayout, @NonNull SwipeRefreshCallback mSwipeRefreshCallback) {
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
        this.mSwipeRefreshCallback = mSwipeRefreshCallback;

        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshCallback.refreshData();
                        hideRefreshLoading();
                    }
                }, DELAY);
            }
        });
    }

    public void hideRefreshLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void disableRefresh() {
        mSwipeRefreshLayout.setEnabled(false);
    }

    public void enableRefresh() {
        mSwipeRefreshLayout.setEnabled(true);
    }

}
