package com.polyhose.utility;

import android.text.TextUtils;
import android.util.Log;

import com.polyhose.data.model.response.APIError;
import com.polyhose.data.retrofitclient.RetrofitClient;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;


public class APIErrorUtil {

    private static final String TAG = "APIErrorUtil";

    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter =
                RetrofitClient.getRetrofit()
                        .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error = null;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
//            Logger.e(e.getMessage());

            Log.e(TAG, "parseError: "+e.getMessage() );

        }

        if (error == null) {
            error = getDefaultError(null);
        }

        return error;
    }

    public static APIError getDefaultError(String message){
        APIError apiError = new APIError();
        if (TextUtils.isEmpty(message)) {
            apiError.setMessage("Error occured, Please try again later.");
        } else {
            apiError.setMessage(message);
        }
        return apiError;
    }
}
