package com.aclocationtrack.base;

import okhttp3.ResponseBody;
import retrofit2.Response;

public abstract class BaseNetworkActivity extends BaseActivity {


    public void serverError(Response<?> response, boolean isToastMsg) {


//        ResponseBody baseResponse = APIErrorUtil.cloneResponseBody(response);
//        BaseApiError baseApiError = APIErrorUtil.parseErrorTest(BaseApiError.class, baseResponse);
//        if (baseApiError != null) {
//
//            if (isToastMsg)
//                showToast(baseApiError.getMessage());
//
//        }
        parseServerError(response, isToastMsg);
    }

    public abstract void parseServerError(Response<?> response, boolean isToastMsg);

    public void onTimeout(boolean isToastMsg) {

        if (isToastMsg) {

            showToast("Timeout");
        } else {
//            showViewError("Timeout");
        }

    }

    public void onNetworkError(boolean isToastMsg) {

        if (isToastMsg) {

            showToast("There is no internet connection");

        } else {
//            showViewError("There is no internet connection");
        }

    }

    public void onUnknownError(String message, boolean isToastMsg) {

        if (isToastMsg) {

            showToast(message);

        } else {

//            showViewError(message);

        }


    }

}
