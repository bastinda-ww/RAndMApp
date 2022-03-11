package com.example.randmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.randmapp.api.CharacterAPI;
import com.example.randmapp.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public static RecyclerView characterRecyclerView;
    public static RecyclerView pageRecyclerView;
    public static CharacterAdapter characterAdapter;
    public static PageAdapter pageAdapter;
    public static List<Result> listOfCharacter;

    //для данных с апи
    private static CharacterAPI characterAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerViewForCharacters();

        initRecyclerViewForPages();

        //для данных с апи
        characterAPI = RestService.getCharacters();
        characterAPI.getCharacters(1).enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if (response.isSuccessful()) {
                    //characterRecyclerView.getAdapter().notifyDataSetChanged();
                    listOfCharacter = response.body().getResults();
                    characterAdapter = new CharacterAdapter(listOfCharacter);
                    characterRecyclerView.setAdapter(characterAdapter);
                    pageAdapter = new PageAdapter(response.body().getInfo().getPages());
                    pageRecyclerView.setAdapter(pageAdapter);
                }
            }
            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable throwable) {
                System.out.println("Throwable " + throwable);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void initRecyclerViewForCharacters() {
        characterRecyclerView = findViewById(R.id.charactersRecyclerView);
        LinearLayoutManager linearLayoutManagerForCharacters = new GridLayoutManager(this, 2);
        characterRecyclerView.setLayoutManager(linearLayoutManagerForCharacters);
    }

    private void initRecyclerViewForPages() {
        pageRecyclerView = findViewById(R.id.pagesRecyclerView);
        LinearLayoutManager linearLayoutManagerFogPages = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        pageRecyclerView.setLayoutManager(linearLayoutManagerFogPages);
    }
}