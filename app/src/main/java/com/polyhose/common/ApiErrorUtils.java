package com.polyhose.common;

import android.text.TextUtils;

import com.polyhose.data.model.errorresponse.CommonApiError;
import com.polyhose.data.model.errorresponse.LoginMessageApiError;
import com.polyhose.data.model.errorresponse.LoginStringApiError;
import com.polyhose.data.retrofitclient.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ApiErrorUtils {


    public static void showLoginApiError(Throwable throwable) {

        Converter<ResponseBody, CommonApiError> converter =
                RetrofitClient.getRetrofit()
                        .responseBodyConverter(CommonApiError.class, new Annotation[0]);

        Response<?> response = ((HttpException) throwable).response();

        CommonApiError error = null;
        try {
            error = converter.convert(response.errorBody());


            if (error instanceof LoginStringApiError) {

            } else {

            }


        } catch (IOException e) {


        }


    }

    public static CommonApiError parseLoginError(Throwable throwable) {

        Response<?> response = ((HttpException) throwable).response();

//        Converter<ResponseBody, APIError> converter =
//                RetrofitClient.getRetrofit()
//                        .responseBodyConverter(APIError.class, new Annotation[0]);

        Gson userDeserializer = new GsonBuilder().setLenient().registerTypeAdapter(CommonApiError.class, new UserResponseDeserializer()).create();


        CommonApiError error = null;
        try {
            error = userDeserializer.fromJson(response.errorBody().string(), CommonApiError.class);
        } catch (IOException e) {

        }

        if (error == null) {
            error = getDefaultError(null);
        }

        return error;
    }


    public static CommonApiError getUserProfileApiError(Throwable throwable) {

        Converter<ResponseBody, CommonApiError> converter =
                RetrofitClient.getRetrofit()
                        .responseBodyConverter(CommonApiError.class, new Annotation[0]);

        Response<?> response = ((HttpException) throwable).response();

        CommonApiError error = null;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {

            error = new CommonApiError();
            error.setMessage("Something went wrong ,Please try again later");

        }

        return error;

    }


    public static CommonApiError getDefaultError(String message) {
        CommonApiError apiError = new CommonApiError();
        if (TextUtils.isEmpty(message)) {
            apiError.setMessage("Error occured, Please try again later.");
        } else {
            apiError.setMessage(message);
        }
        return apiError;
    }

    public static class UserResponseDeserializer implements JsonDeserializer<CommonApiError> {
        @Override
        public CommonApiError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


//            ResponseWrapper responseWrapper = new Gson().fromJson(json, ResponseWrapper.class);


            if (((JsonObject) json).get("data") == null) {

                return new Gson().fromJson(json, CommonApiError.class);

            } else if (((JsonObject) json).get("data") instanceof JsonObject) {


                return new Gson().fromJson(json, LoginMessageApiError.class);
            } else {
                return new Gson().fromJson(json, LoginStringApiError.class);
            }

        }
    }


}
