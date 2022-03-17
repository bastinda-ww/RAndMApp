package com.example.randmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {
    private ImageView characterImage;
    private TextView characterName;
    private TextView characterLocation;
    private TextView characterSpecies;
    private TextView characterStatus;
    private Button toCharacterActivity;
    private Button toEpisodeActivity;
    private String idOfCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_of_character);
        characterImage = findViewById(R.id.imageOfCharacter);
        characterName = findViewById(R.id.nameOfCharacter);
        characterLocation = findViewById(R.id.locationOfCharacter);
        characterSpecies = findViewById(R.id.speciesOfCharacter);
        characterStatus = findViewById(R.id.statusOfCharacter);

        loadingData();
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
                    intent.putExtra("characterId", idOfCharacter);
                    startActivity(intent);
                    finish();
                } catch (Exception exception) {

                }
            }
        });
    }

    private void loadingData() {

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(DescriptionActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("");
        progressDialog.setTitle("Loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        Picasso.with(this).load(getIntent().getStringExtra("characterImage")).into(characterImage, new Callback() {
            @Override
            public void onSuccess() {
                progressDialog.dismiss();
            }

            @Override
            public void onError() {
//                progressDialog.dismiss();
            }
        });
        characterName.setText("Name:          " + getIntent().getStringExtra("characterName"));
        characterLocation.setText("Location:     " + getIntent().getStringExtra("characterLocation"));
        characterSpecies.setText("Species:       " + getIntent().getStringExtra("characterSpecies"));
        characterStatus.setText("Status:          " + getIntent().getStringExtra("characterStatus"));
        idOfCharacter = getIntent().getStringExtra("characterId");
        Toast.makeText(this, "id " + idOfCharacter, Toast.LENGTH_SHORT).show();
    }
}