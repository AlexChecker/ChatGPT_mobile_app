package com.example.chatgptapp;

import android.app.Service;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestBuilder {
    private static String URL="https://api.openai.com";

    private static API REST_CLIENT;

    static {
        setupRestClient();
    }

    public static void setupRestClient()
    {
        OkHttpClient okhttp = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).client(okhttp).build();

        REST_CLIENT = retrofit.create(API.class);

    }

    public static API getRestClient()
    {
        return REST_CLIENT;
    }
}