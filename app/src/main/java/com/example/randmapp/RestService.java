package com.example.randmapp;

import com.example.randmapp.api.CharacterAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {
    static final String BASE_URL = "https://rickandmortyapi.com/";

    public static CharacterAPI getCharacters() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CharacterAPI characterAPI = retrofit.create(CharacterAPI.class);
        return characterAPI;

    }
}