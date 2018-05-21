package com.midainc.callassistant.api;


import com.midainc.callassistant.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiModule {

    private static NetDataManager mNetDataManager;

    public static NetDataManager provideNetDataManager() {
        if (mNetDataManager == null) {
            synchronized (ApiModule.class) {
                mNetDataManager = new NetDataManager(provideApiService());
            }
        }
        return mNetDataManager;
    }

    private static ApiService provideApiService() {
        return provideRetrofit().create(ApiService.class);
    }

    private static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(provideBaseUrl())
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient()
                .newBuilder()
                .addNetworkInterceptor(provideNetWorkInterceptor())
                .addInterceptor(provideHttpLoggingInterceptor())
                .connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .build();
    }

    private static Interceptor provideNetWorkInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                return response.newBuilder()
                        .addHeader("Accept", "application/json;charset=utf-8")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("token", "5a6e8861711d1c0007bcbdfbea3ae04c")
                        .build();
            }
        };
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ?
                        HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    private static String provideBaseUrl() {
        return "http://115.159.26.196:8080";
    }

}
