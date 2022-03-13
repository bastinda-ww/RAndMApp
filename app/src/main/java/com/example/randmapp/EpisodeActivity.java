package com.example.randmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private List<Episode> episodeArrayList;
    private API episodeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_episodes);
        toCharacterActivity = findViewById(R.id.toCharacterActivity);

        initRecyclerView();

        episodeAPI = RestService.getData();
        episodeAPI.getEpisodes(1).enqueue(new Callback<ResponseEpisodesDTO>() {
            @Override
            public void onResponse(Call<ResponseEpisodesDTO> call, Response<ResponseEpisodesDTO> response) {
                if (response.isSuccessful()) {
                    episodeArrayList = response.body().getEpisodes();
                    episodeAdapter = new EpisodeAdapter(episodeArrayList);
                    episodeRecyclerView.setAdapter(episodeAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseEpisodesDTO> call, Throwable throwable) {
                System.out.println("Throwable " + throwable);
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        return true;

    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        List<Episode> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (Episode item : episodeArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            episodeAdapter.filterList(filteredlist);
        }
    }

    private void initRecyclerView() {

        // below line is to add data to our array list.
//        episodeArrayList.add(new Episode(1, "20 minute's adventure", "10.10.2020"));
//        episodeArrayList.add(new Episode(2, "first Ep.", "10.10.2022"));
//        episodeArrayList.add(new Episode(3, "second Ep.", "10.03.2021"));
//        episodeArrayList.add(new Episode(4, "Self Paced Course", "01.12.2020"));
//        episodeArrayList.add(new Episode(5, "third Ep.", "08.10.2020"));
//        episodeArrayList.add(new Episode(6, "The end of the Citadel", "31.01.2020"));
//        episodeArrayList.add(new Episode(7, "my favorite Ep.", "31.01.2020"));
//        episodeArrayList.add(new Episode(8, "DSA Self Paced Course", "10.12.2020"));
//        episodeArrayList.add(new Episode(9, "DSA d Course", "10.12.2020"));
//        episodeArrayList.add(new Episode(10, "DSA Self Paced Course", "10.12.2020"));
//        episodeArrayList.add(new Episode(11, "D Selrse", "10.12.2020"));
//        episodeArrayList.add(new Episode(12, "DSA SePaced Course", "10.12.2020"));
//        episodeArrayList.add(new Episode(13, " Self Paced Course", "10.12.2020"));
//        episodeArrayList.add(new Episode(14, "Paced Course", "10.12.2020"));
//        episodeArrayList.add(new Episode(15, "Self", "10.12.2020"));

        episodeRecyclerView = findViewById(R.id.episodeRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        episodeRecyclerView.setHasFixedSize(true);
        episodeRecyclerView.setLayoutManager(manager);
    }
}