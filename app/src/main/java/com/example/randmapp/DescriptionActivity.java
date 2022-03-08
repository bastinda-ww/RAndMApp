package com.example.randmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {
    private ImageView characterImage;
    private TextView characterName;
    private TextView characterLocation;
    private TextView characterSpecies;
    private TextView characterStatus;
    private Button toCharacterActivity;
    private Button toEpisodeActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_of_character);
        characterImage = findViewById(R.id.imageOfCharacter);
        characterName = findViewById(R.id.nameOfCharacter);
        characterLocation = findViewById(R.id.locationOfCharacter);
        characterSpecies = findViewById(R.id.speciesOfCharacter);
        characterStatus = findViewById(R.id.statusOfCharacter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        toCharacterActivity = findViewById(R.id.toCharacterActivity);
        toCharacterActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception exception) {

                }
            }
        });

        toEpisodeActivity = findViewById(R.id.toEpisodeActivity);
        toEpisodeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(DescriptionActivity.this, EpisodeActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception exception) {

                }
            }
        });
    }
}