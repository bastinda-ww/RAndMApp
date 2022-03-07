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
    private ImageLoader someImageLoader;
    private NetworkImageView someNetworkImageView;
    private TextView nameOfCharacter;
    private int countOfPage = 10;
    //    String url = "https://cameralabs.org/media/lab16/post/03-16/15/kon-v-kostyume_1.jpg";
    String url1 = "https://rickandmortyapi.com/api/character/avatar/1.jpeg";
    private List<Character> listOfCharacter = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //someNetworkImageView = findViewById(R.id.characterImage);
        //nameOfCharacter = findViewById(R.id.characterName);

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
        //someImageLoader = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getImageLoader();
        //Image URL - This can point to any image file supported by Android
//        final String url = "http://goo.gl/0rkaBz";
        //someImageLoader.get(url1, ImageLoader.getImageListener(someNetworkImageView, R.mipmap.icon_user_default, android.R.drawable.ic_dialog_alert));
        //someNetworkImageView.setImageUrl(url1, someImageLoader);

        Location someLocation = new Location(1, "Samara");
        Character character0 = new Character(1, "Igor", "alive", "human", someLocation, "URL", someImageLoader);
        Character character1 = new Character(2, "Andrey", "alive", "human", someLocation, "URL", someImageLoader);
        Character character2 = new Character(3, "Max", "alive", "human", someLocation, "URL", someImageLoader);
        Character character3 = new Character(4, "Factor", "alive", "human", someLocation, "URL", someImageLoader);
        Character character4 = new Character(5, "Cris", "alive", "human", someLocation, "URL", someImageLoader);

        listOfCharacter.add(character0);
        listOfCharacter.add(character1);
        listOfCharacter.add(character2);
        listOfCharacter.add(character3);
        listOfCharacter.add(character4);

        characterAdapter = new CharacterAdapter(listOfCharacter);
        characterList.setAdapter(characterAdapter);

        pageAdapter = new PageAdapter(countOfPage);
    }
}