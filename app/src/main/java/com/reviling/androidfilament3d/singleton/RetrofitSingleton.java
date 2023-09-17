package com.reviling.androidfilament3d.singleton;


import static com.reviling.androidfilament3d.utils.Constant.BASE_URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitSingleton {

//  private static OkHttpClient client = new OkHttpClient.Builder()
//          .connectTimeout(100, TimeUnit.SECONDS)
//          .readTimeout(100, TimeUnit.SECONDS).build();

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }




    public static <S> S createService(Class<S> seviceClass) {

        return retrofit.create(seviceClass);
    }
}
