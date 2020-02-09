package com.example.admin.mobile_shofa13.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitService {
    private static String URL = "http://22ce55ee.ngrok.io/";

    public static Retrofit setInit(){
        return new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiFilm getInstance(){
        return setInit().create(ApiFilm.class);
    }

    public static String getUrl(){
        return URL;
    }
}
