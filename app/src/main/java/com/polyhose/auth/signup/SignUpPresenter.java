package com.polyhose.auth.signup;

import android.util.Log;

import com.polyhose.base.BaseActivity;
import com.polyhose.base.BasePresenter;
import com.polyhose.data.DataSource;
import com.polyhose.data.model.response.APIError;
import com.polyhose.data.model.response.RegisterResponse;

public class SignUpPresenter extends BasePresenter {

    private static final String TAG = "SignUpPresenter";
    private DataSource dataSource;


    public SignUpPresenter(DataSource dataSource, BaseActivity signUpActivity) {

        super(signUpActivity);
        this.dataSource = dataSource;
    }

    public void callRegister(String json, final ResponseCallBack responseCallBack) {


        Log.e(TAG, "callRegister: " + json);

        baseActivity.showLoading();
//        dataSource.register(json, new DataListener() {
//            @Override
//            public void onSuccess(Object object) {
//                baseActivity.hideLoading();
//
//                if (object instanceof RegisterResponse) {
//
//                    responseCallBack.onSuccess((RegisterResponse) object);
//
//                    Log.e(TAG, "onSuccess: RegisterResponse called" + ((RegisterResponse) object).getMessage());
//
//                }
//
//            }
//
//            @Override
//            public void onFail(Throwable throwable) {
//                baseActivity.hideLoading();
//                if (throwable instanceof HttpException) {
//
//                    APIError apiError = APIErrorUtil.parseError(((HttpException) throwable).response());
//
////                    Log.e(TAG, "onFail: " + apiError.getMessage() + "  " + apiError.getSuccess() + " " + new Gson().toJson(apiError.getData()).toString());
//
//                    responseCallBack.onValidationError(apiError);
////                    baseActivity.showToast(apiError.getData().getMobileNo().get(0));
//
//                } else {
//                    baseActivity.showToast(throwable.getMessage());
//                    responseCallBack.onError(throwable);
////                    Log.e(TAG, "onFail: throwable " + throwable.getMessage());
//                }
//
//            }
//
//            @Override
//            public void onNetworkFailure() {
//                baseActivity.hideLoading();
//                responseCallBack.noNetwork();
////                baseActivity.showToast("There is no internet connection");
////                Log.e(TAG, "onNetworkFailure: ");
//            }
//        });


    }

    interface ResponseCallBack {

        void onValidationError(APIError apiError);

        void onError(Throwable throwable);

        void onSuccess(RegisterResponse registerResponse);

        void noNetwork();
    }
}
