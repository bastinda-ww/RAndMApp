package com.example.randmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.randmapp.api.API;
import com.example.randmapp.model.Character;

import java.sql.SQLOutput;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView characterRecyclerView;
    private RecyclerView pageRecyclerView;
    private CharacterAdapter characterAdapter;
    private PageAdapter pageAdapter;
    public PageAdapter.OnPageClickListener onPageClickListener;
    private List<Character> listOfCharacter;
    private static int initialPage = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            getDataFromAPI(initialPage);
            initRecyclerViewForCharacters();
            initRecyclerViewForPages();
        } catch (Exception e) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            onPageClickListener = new PageAdapter.OnPageClickListener() {
                @Override
                public void onPageClick(int page) {
                    try {
                        getDataFromAPI(page);
                    } catch (Exception exception) {
                        Toast.makeText(MainActivity.this, "exception 33", Toast.LENGTH_SHORT).show();
//                        throw exception;
                    }
                }
            };
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "exception 33", Toast.LENGTH_SHORT).show();
        }

    }

    private void initRecyclerViewForCharacters() {
        characterRecyclerView = findViewById(R.id.charactersRecyclerView);
        characterRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void initRecyclerViewForPages() {
        pageRecyclerView = findViewById(R.id.pagesRecyclerView);
        pageRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void getDataFromAPI(int page) {
        API characterAPI = RestService.getData();
        characterAPI.getCharacters(page).enqueue(new Callback<ResponseCharactersDTO>() {
            @Override
            public void onResponse(Call<ResponseCharactersDTO> call, Response<ResponseCharactersDTO> response) {
                if (response.isSuccessful()) {
                    listOfCharacter = response.body().getCharacters();
                    CharacterAdapter.OnCharacterClickListener onCharacterClickListener = new CharacterAdapter.OnCharacterClickListener() {
                        @Override
                        public void onCharacterClick(Character character) {
                            try {
                                Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
                                intent.putExtra("characterImage", character.getImage());
                                intent.putExtra("characterName", character.getName());
                                intent.putExtra("characterLocation", character.getLocation().getName());
                                intent.putExtra("characterSpecies", character.getSpecies());
                                intent.putExtra("characterStatus", character.getStatus());
                                intent.putExtra("characterId", String.valueOf(character.getId()));
                                startActivity(intent);
//                                Toast.makeText(MainActivity.this, "Character" + character.getName(), Toast.LENGTH_SHORT).show();
                                finish();
                            } catch (Exception exception) {

                            }
                        }
                    };
                    pageAdapter = new PageAdapter(response.body().getInfo().getPages(), onPageClickListener);
                    pageRecyclerView.setAdapter(pageAdapter);
                    characterAdapter = new CharacterAdapter(listOfCharacter, onCharacterClickListener);
                    characterRecyclerView.setAdapter(characterAdapter);
                } else {
                    System.out.println("онРеспонс иначе");
                }
            }
            @Override
            public void onFailure(Call<ResponseCharactersDTO> call, Throwable throwable) {
                System.out.println("Throwable " + throwable);
            }
        });
    }
}