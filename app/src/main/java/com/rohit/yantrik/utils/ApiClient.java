package com.rohit.yantrik.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 ************************************************************
 * Project : Yan3k
 * Created By : Rohit Ingulkar
 ************************************************************
 **/
public class ApiClient {

    private static final int TIMEOUT = 600;
    private static Retrofit retrofit = null;



    public static Retrofit getApiClient(String url) {
        if (retrofit == null) {
            Builder builder = null;

            if(builder==null){
                builder = new Builder();
            }
            OkHttpClient localOkHttpClient = builder.connectTimeout(100L, TimeUnit.SECONDS).readTimeout(100L, TimeUnit.SECONDS).build();
            final Gson gson =
                    new GsonBuilder().setLenient().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(localOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return retrofit;
    }
}