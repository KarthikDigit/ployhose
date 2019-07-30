package com.polyhose.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.polyhose.R;

import butterknife.BindView;
import retrofit2.Response;

public abstract class BaseSwipeRefershFragment extends BaseMultiStateFragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.base_swipe_ListView)
    protected RecyclerView baseSwipeListView;
    @BindView(R.id.pullToRefresh)
    protected SwipeRefreshLayout pullToRefresh;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_swipe_refresh_layout;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        baseSwipeListView = view.findViewById(R.id.base_swipe_ListView);
//        pullToRefresh = view.findViewById(R.id.pullToRefresh);

        if (pullToRefresh != null)
            pullToRefresh.setOnRefreshListener(this);

    }


    public void setPullToRefreshEnabled(boolean isEnabled) {
        if (pullToRefresh != null)
            pullToRefresh.setEnabled(isEnabled);

    }

    public void setPullToRefresh(boolean isRefresh) {

        if (pullToRefresh != null)
            pullToRefresh.setRefreshing(isRefresh);
    }

    @Override
    public void onRefresh() {

        onRetryOrCallApi();
    }

    @Override
    protected void onRetryOrCallApi() {

        onRetryOrCallApiWithSwipeToRefesh(true);

    }

    protected abstract void onRetryOrCallApiWithSwipeToRefesh(boolean isSwipe);


    public void showSwipeOrLoading(boolean isSwipe) {

        if (isSwipe) {

            setPullToRefresh(true);

        } else {

            showViewLoading();
        }

    }

    public void showContentAndHideSwipe() {

        setPullToRefresh(false);
        showViewContent();
    }

    @Override
    public void serverError(Response<?> response, boolean isToastMsg) {
        super.serverError(response, isToastMsg);

        setPullToRefresh(false);
    }

    @Override
    public void onUnknownError(String message, boolean isToastMsg) {
        super.onUnknownError(message, isToastMsg);

        setPullToRefresh(false);
    }

    @Override
    public void onTimeout(boolean isToastMsg) {
        super.onTimeout(isToastMsg);

        setPullToRefresh(false);
    }

    @Override
    public void onNetworkFailure() {
        super.onNetworkFailure();

        setPullToRefresh(false);
    }
}
