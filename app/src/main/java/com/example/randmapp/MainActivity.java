package com.example.randmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.randmapp.api.API;
import com.example.randmapp.model.Character;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static RecyclerView characterRecyclerView;
    public static RecyclerView pageRecyclerView;
    public static CharacterAdapter characterAdapter;
    public static PageAdapter pageAdapter;
    public static List<Character> listOfCharacter;
    private Response<ResponseCharactersDTO> response;

    //для данных с апи
    private static API characterAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromAPI();
        initRecyclerViewForCharacters();
        initRecyclerViewForPages();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //add clickListener for pageRecyclerView
    }

    private void initRecyclerViewForCharacters() {
        characterRecyclerView = findViewById(R.id.charactersRecyclerView);
        LinearLayoutManager linearLayoutManagerForCharacters = new GridLayoutManager(this, 2);
        characterRecyclerView.setLayoutManager(linearLayoutManagerForCharacters);
        listOfCharacter = response.body().getCharacters();
        //add character click listener
        CharacterAdapter.OnCharacterClickListener onCharacterClickListener = new CharacterAdapter.OnCharacterClickListener() {
            @Override
            public void onCharacterClick(Character character) {
                Toast.makeText(MainActivity.this, "Character" + character, Toast.LENGTH_SHORT).show();
            }
        };
        characterAdapter = new CharacterAdapter(listOfCharacter, onCharacterClickListener);
        characterRecyclerView.setAdapter(characterAdapter);
    }

    private void initRecyclerViewForPages() {
        pageRecyclerView = findViewById(R.id.pagesRecyclerView);
        LinearLayoutManager linearLayoutManagerFogPages = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        pageRecyclerView.setLayoutManager(linearLayoutManagerFogPages);
        pageAdapter = new PageAdapter(response.body().getInfo().getPages());
        pageRecyclerView.setAdapter(pageAdapter);
    }

    private void getDataFromAPI() {
        characterAPI = RestService.getData();
        characterAPI.getCharacters(1).enqueue(new Callback<ResponseCharactersDTO>() {
            @Override
            public void onResponse(Call<ResponseCharactersDTO> call, Response<ResponseCharactersDTO> response) {
                if (response.isSuccessful()) {
                    //characterRecyclerView.getAdapter().notifyDataSetChanged();
                    setResponse(response);
                }
            }

            @Override
            public void onFailure(Call<ResponseCharactersDTO> call, Throwable throwable) {
                System.out.println("Throwable " + throwable);
            }
        });
    }

    private void setResponse(Response response) {
        this.response = response;
    }
}