package com.example.randmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EpisodeActivity extends AppCompatActivity {
    private RecyclerView episodeRecyclerView;
    private Button toCharacterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_episodes);
        episodeRecyclerView = findViewById(R.id.episodeRecyclerView);
        toCharacterActivity = findViewById(R.id.toCharacterActivity);

    }

    @Override
    protected void onStart() {
        super.onStart();

        toCharacterActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(EpisodeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception exception) {

                }
            }
        });
    }
}