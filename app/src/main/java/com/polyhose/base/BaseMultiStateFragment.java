package com.polyhose.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.polyhose.R;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.customview.MultiStateView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;

public abstract class BaseMultiStateFragment extends BaseFragment {


    Unbinder unbinder;
    private MultiStateView multiStateView;

    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(getLayoutId(), container, false);

        multiStateView = view.findViewById(R.id.multiStateView);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }


    protected abstract void initViews();

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (unbinder != null) unbinder.unbind();
    }

    public void showViewLoading() {

        if (multiStateView != null)
            multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    public void showViewContent() {
        if (multiStateView != null)
            multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    public void showViewError() {

        if (multiStateView != null) {
            multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
            View view = multiStateView.getView(MultiStateView.VIEW_STATE_ERROR);
            Button retry = view.findViewById(R.id.retry);

            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRetryOrCallApi();
                }
            });
        }
    }

    public void showViewError(String errorMsg) {

        if (multiStateView != null) {
            multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);

            View view = multiStateView.getView(MultiStateView.VIEW_STATE_ERROR);

            TextView eMsgView = view.findViewById(R.id.errorMsg);
            Button retry = view.findViewById(R.id.retry);

            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRetryOrCallApi();
                }
            });
            eMsgView.setText(errorMsg);
        }

    }

    public void showViewEmpty() {
        if (multiStateView != null)
            multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    public void showViewEmpty(String emptyMsg) {
        if (multiStateView != null) {
            multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
            View view = multiStateView.getView(MultiStateView.VIEW_STATE_EMPTY);

            TextView empty = null;
            if (view != null) {
                empty = view.findViewById(R.id.emptyMsg);
                empty.setText(emptyMsg);
            }

        }
    }

    protected abstract void onRetryOrCallApi();


    @Override
    public void serverError(Response<?> response, boolean isToastMsg) {

        String errorMsg = MyCallBackWrapper.getErrorMessage(response.errorBody());


        showViewError(errorMsg);


        showToast("Server Called");

    }

    @Override
    public void parseServerError(Response<?> response, boolean isToastMsg) {

    }

    @Override
    public void onTimeout(boolean isToastMsg) {

        if (isToastMsg) {

            showToast("Timeout");
        } else {
            showViewError("Timeout");
        }

    }

    @Override
    public void onNetworkError(boolean isToastMsg) {

        if (isToastMsg) {

            showToast("There is no internet connection");

        } else {

            showViewError("There is no internet connection");

        }

    }

    @Override
    public void onUnknownError(String message, boolean isToastMsg) {

        if (isToastMsg) {

            showToast(message);
        } else {
            showViewError(message);
        }


        showToast("OnUnknow");

    }

}
