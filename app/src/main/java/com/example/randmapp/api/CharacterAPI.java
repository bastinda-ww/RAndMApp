package com.example.randmapp.api;

import com.example.randmapp.ResponseDTO;
import com.example.randmapp.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharacterAPI {

    @GET("api/character")
    Call<ResponseDTO> getCharacters(@Query("page") int numberOfPage);
}
