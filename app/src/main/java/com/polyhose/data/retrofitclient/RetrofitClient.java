package com.polyhose.data.retrofitclient;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by yasar on 27/2/18.
 */

public class RetrofitClient {

    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;
    private static String url = "";

    private RetrofitClient(String url) {

        this.url = url;

    }

    public static RetrofitClient getRetrofitClientInstance(String url) {

        if (retrofitClient == null) {

            retrofitClient = new RetrofitClient(url);

        }

        return retrofitClient;
    }

    public static Retrofit getRetrofit() {

        if (retrofit == null) {

//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

//            ConnectionSpec spec = new
//                    ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
//                    .tlsVersions(TlsVersion.TLS_1_0, TlsVersion.TLS_1_1, TlsVersion.TLS_1_2, TlsVersion.TLS_1_3)
//                    .cipherSuites(
//                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
//                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
//                            CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256,
//                            CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA,
//                            CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA256,
//                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
//                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
//                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
//                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
//                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
//                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
//                            CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
//                            CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
//                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA
//
//                    )
//                    .build();

//            OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectionSpecs(Collections.singletonList(spec)).connectTimeout(60, TimeUnit.SECONDS)
//                    .writeTimeout(60, TimeUnit.SECONDS)
//                    .readTimeout(60, TimeUnit.SECONDS);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS);

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(logging);

//            httpClient.addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request.Builder ongoing = chain.request().newBuilder();
//                    ongoing.addHeader("Authorization", getToken(app));
//                    return chain.proceed(ongoing.build());
//                }
//            });

//            httpClient.addInterceptor(logging);


//            Gson gson = new GsonBuilder().setLenient().registerTypeAdapter(APIError.class, new APIErrorResponseDeserializer()).create();


            retrofit = new Retrofit.Builder()
                    .client(httpClient.build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(url)
                    .build();

        }

        return retrofit;

    }

//    public static class APIErrorResponseDeserializer implements JsonDeserializer<APIError> {
//        @Override
//        public APIError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//
//
//            if (((JsonObject) json).get("data") instanceof JsonObject) {
//                return new Gson().fromJson(json, APIError.Data.class);
//            } else {
//                return new Gson().fromJson(json, APIError.DataString.class);
//            }
//
//        }
//    }

}
