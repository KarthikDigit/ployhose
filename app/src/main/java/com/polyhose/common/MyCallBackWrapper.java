package com.polyhose.common;


import android.content.Context;

import com.polyhose.base.BaseView;
import com.polyhose.utility.ProgressUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

public abstract class MyCallBackWrapper<T> extends DisposableObserver<T> {


    private WeakReference<Context> context;
    private WeakReference<BaseView> baseView;
    private boolean isLoading = false;
    private boolean isToastMsg = false;


    public MyCallBackWrapper(Context context, BaseView baseView, boolean isLoading, boolean isToastMsg) {
        this.context = new WeakReference<>(context);
        this.baseView = new WeakReference<>(baseView);
        this.isLoading = isLoading;
        this.isToastMsg = isToastMsg;

        if (isLoading) ProgressUtils.showProgress(this.context.get(), "Loading");
    }

    @Override
    public void onNext(T t) {

        if (isLoading) ProgressUtils.hideProgress();
        //You can return StatusCodes of different cases from your API and handle it here. I usually include these cases on BaseResponse and iherit it from every Response
        onSuccess(t);
    }

    public abstract void onSuccess(T t);

    @Override
    public void onError(Throwable e) {


        if (isLoading) ProgressUtils.hideProgress();
        if (e instanceof HttpException) {


            baseView.get().serverError(((HttpException) e).response(), this.isToastMsg);
//            ResponseBody responseBody = ((HttpException) e).response().errorBody();
//            reference.get().serverError(getErrorMessage(responseBody));
        } else if (e instanceof SocketTimeoutException) {
            baseView.get().onTimeout(this.isToastMsg);
        } else if (e instanceof IOException) {
            baseView.get().onNetworkError(this.isToastMsg);
        } else {
            baseView.get().onUnknownError(e.getMessage(), this.isToastMsg);
        }
    }

    @Override
    public void onComplete() {

    }

    public static String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return "Something went wrong, " + e.getMessage();
        }
    }

//    public static String getErrorMessage(String responseBody) {
//        try {
//            JSONObject jsonObject = new JSONObject(responseBody);
//            return jsonObject.getString("message");
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
}
