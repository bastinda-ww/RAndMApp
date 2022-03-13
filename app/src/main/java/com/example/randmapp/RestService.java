package com.example.randmapp;

import com.example.randmapp.api.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {
    static final String BASE_URL = "https://rickandmortyapi.com/";

    public static API getData() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);
        return api;

    }
}