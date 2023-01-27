package com.cookandroid.retrofitmovie;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {
    private static Retrofit zretrofit = null;

    static Retrofit getClient(){
        //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();

        zretrofit = new Retrofit.Builder()
                .baseUrl("https://yts-proxy.now.sh/list_movies.json?sort_by=rating")
                // http://mellowcode.org/json/students/
        //        .baseUrl("http://mellowcode.org/")
                // .baseUrl("https://yts-proxy.now.sh/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return zretrofit;
    }
}
