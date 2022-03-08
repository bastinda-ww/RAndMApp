package com.example.randmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView characterList;
    private RecyclerView pageList;
    private CharacterAdapter characterAdapter;
    private PageAdapter pageAdapter;
    private int countOfPage = 10;
    String url1 = "https://rickandmortyapi.com/api/character/avatar/1.jpeg";
    private ArrayList<Character> listOfCharacter = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        characterList = findViewById(R.id.charactersRecyclerView);
        LinearLayoutManager linearLayoutManagerForCharacters = new GridLayoutManager(this, 2);
        characterList.setLayoutManager(linearLayoutManagerForCharacters);

        pageList = findViewById(R.id.pagesRecyclerView);
        LinearLayoutManager linearLayoutManagerFogPages = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        pageList.setLayoutManager(linearLayoutManagerFogPages);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Location someLocation = new Location(1, "Samara");
        Character character0 = new Character(1, "Igor", "alive", "human", someLocation, "https://rickandmortyapi.com/api/character/avatar/1.jpeg");
        Character character1 = new Character(2, "Andrey", "alive", "human", someLocation, "URL");
        Character character2 = new Character(3, "Max", "alive", "human", someLocation, "URL");
        Character character3 = new Character(4, "Factor", "unknown", "human", someLocation, "https://rickandmortyapi.com/api/character/avatar/2.jpeg");
        Character character4 = new Character(5, "Biba", "alive", "human", someLocation, "URL");
        Character character5 = new Character(6, "Dimas", "alive", "machine", someLocation, "URL");
        Character character6 = new Character(7, "Boba", "alive", "alien", someLocation, "URL");
        Character character7 = new Character(8, "J.I.Joe", "alive", "human", someLocation, "URL");
        Character character8 = new Character(9, "Ed", "alive", "human", someLocation, "URL");
        Character character9 = new Character(10, "Lola", "alive", "human", someLocation, "URL");
        Character character10 = new Character(11, "Joe", "alive", "human", someLocation, "URL");

        listOfCharacter.add(character0);
        listOfCharacter.add(character1);
        listOfCharacter.add(character2);
        listOfCharacter.add(character3);
        listOfCharacter.add(character4);
        listOfCharacter.add(character5);
        listOfCharacter.add(character6);
        listOfCharacter.add(character7);
        listOfCharacter.add(character8);
        listOfCharacter.add(character9);
        listOfCharacter.add(character10);

        characterAdapter = new CharacterAdapter(listOfCharacter);
        characterList.setAdapter(characterAdapter);
        pageAdapter = new PageAdapter(countOfPage);
    }
}