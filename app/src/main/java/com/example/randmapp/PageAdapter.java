package com.example.randmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randmapp.api.CharacterAPI;
import com.example.randmapp.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.randmapp.MainActivity.characterList;
import static com.example.randmapp.MainActivity.pageList;
import static com.example.randmapp.MainActivity.listOfCharacter;
import static com.example.randmapp.MainActivity.characterAdapter;
import static com.example.randmapp.MainActivity.pageAdapter;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.PageViewHolder> {
    private int countOfPage;
    private int page;
    private ViewGroup parent;

    private final CharacterAPI characterAPI = RestService.getCharacters();
    ;

    private void loadData(int page) {

        characterAPI.getCharacters(page).enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if (response.isSuccessful()) {
                    listOfCharacter = response.body().getResults();
                    characterAdapter = new CharacterAdapter(listOfCharacter);
                    characterList.setAdapter(characterAdapter);
                    pageAdapter = new PageAdapter(10);
                    pageList.setAdapter(pageAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                System.out.println("Throwable " + t);
            }
        });

    }


    public PageAdapter(int countOfPage) {
        this.countOfPage = countOfPage;
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        this.parent = parent;
        int layoutIdForItemPage = R.layout.item_of_page;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForItemPage, parent, false);
        return (new PageViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull PageAdapter.PageViewHolder holder, int position) {
        holder.bind(position + 1);
    }

    @Override
    public int getItemCount() {
        return countOfPage;
    }

    class PageViewHolder extends RecyclerView.ViewHolder {
        TextView numberOfPage;

        public PageViewHolder(@NonNull View itemView) {
            super(itemView);
            numberOfPage = itemView.findViewById(R.id.numberOfPage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //get new list of character from API and show it
                    loadData(5);

                }
            });
        }

        public void bind(int counter) {
            numberOfPage.setText(String.valueOf(counter));

        }
    }
}
