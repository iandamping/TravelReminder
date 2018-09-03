package com.example.jun.travelreminder.network;

import com.example.jun.travelreminder.BuildConfig;
import com.example.jun.travelreminder.MainApplication;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientBaru {
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;

    public static ApiInterface createRequestWeather() {
        return getClient(ApiConfig.BASE_URL).create(ApiInterface.class);
    }

    public static ApiInterface createRequestQuotes() {
        return getClient(ApiConfig.QUOTES_BASE_URL).create(ApiInterface.class);
    }

    public static ApiInterface createRequestNews() {
        return getClient(ApiConfig.NEWS_BASE_URL).create(ApiInterface.class);
    }

    public static ApiInterface createBola() {
        return getClient(ApiConfig.BOLA_URL).create(ApiInterface.class);
    }

    private static Retrofit getClient(String baseUrl) {

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {

        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(MainApplication.CONTEXT.getApplicationContext().getCacheDir(), cacheSize);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        dispatcher.setMaxRequestsPerHost(1);
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .cache(cache)
                .dispatcher(dispatcher);

        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder ongoing = chain.request().newBuilder();

                ongoing.addHeader("Content-Type", "application/json");
//                if (withApiKey) {
//                    ongoing.addHeader("Accept", "application/json;versions=1");
//                    ongoing.addHeader("api-key", ApiConfig.API_KEY_SMARTCOM);
//                } else {
//                    ongoing.addHeader("access-token", ApiConfig.ACCESS_TOKEN);
//                }
                return chain.proceed(ongoing.build());
            }
        });

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpBuilder.addInterceptor(interceptor);
        }
        okHttpClient = okHttpBuilder.build();

        return okHttpClient;
    }

}
