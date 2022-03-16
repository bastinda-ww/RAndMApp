package com.example.randmapp.api;

import com.example.randmapp.ResponseCharactersDTO;
import com.example.randmapp.ResponseEpisodesDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("api/character")
    Call<ResponseCharactersDTO> getCharacters(@Query("page") int numberOfPage);

    @GET("api/episode")
    Call<ResponseEpisodesDTO> getEpisodes(@Query("page") int numberOfPage);
}
