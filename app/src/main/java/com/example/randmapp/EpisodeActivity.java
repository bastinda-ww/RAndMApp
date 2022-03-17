package com.example.randmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.randmapp.api.API;
import com.example.randmapp.model.Episode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeActivity extends AppCompatActivity {
    private RecyclerView episodeRecyclerView;
    private EpisodeAdapter episodeAdapter;
    private Button toCharacterActivity;
    private List<Episode> episodeList = new ArrayList<>();
    private String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_episodes);
        searchString = getIntent().getStringExtra("characterId");
        initButton();

        getDataFromAPI(1);
        getDataFromAPI(2);
        getDataFromAPI(3);

        initRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private List<Episode> filter(List<Episode> episodeList, String text) {
        List<Episode> filteredlist = new ArrayList<>();
        for (Episode item : episodeList) {
            List<String> characters = item.getCharacters();
            for (int i = 0; i < characters.size(); i++) {
                if (characters.get(i).endsWith(text)) {
                    filteredlist.add(item);
                }
            }
        }
        return filteredlist;
    }

    private void initRecyclerView() {
        episodeRecyclerView = findViewById(R.id.episodeRecyclerView);
        episodeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initButton() {
        toCharacterActivity = findViewById(R.id.toCharacterActivity);
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

    private void getDataFromAPI(int page) {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(EpisodeActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("");
        progressDialog.setTitle("Loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        API episodeAPI = RestService.getData();
        episodeAPI.getEpisodes(page).enqueue(new Callback<ResponseEpisodesDTO>() {
            @Override
            public void onResponse(Call<ResponseEpisodesDTO> call, Response<ResponseEpisodesDTO> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    List<Episode> localEp;
                    localEp = filter(response.body().getEpisodes(), "character/" + searchString);
                    episodeList.addAll(localEp);
                    if (page == 3) {
                        if (episodeList.isEmpty()) {
                            Toast.makeText(EpisodeActivity.this, "No Data Found..", Toast.LENGTH_SHORT).show();
                        } else {
                            episodeAdapter = new EpisodeAdapter(episodeList);
                            episodeRecyclerView.setAdapter(episodeAdapter);
                        }
                    }
                } else {
                    Toast.makeText(EpisodeActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEpisodesDTO> call, Throwable throwable) {
                System.out.println("Throwable " + throwable);
            }
        });
    }
}