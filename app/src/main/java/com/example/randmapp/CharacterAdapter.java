package com.example.randmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private String[] name;
    private String[] image;
    private List<Character> listOfCharacter;

    private int countOfHolders;
    private ViewGroup parent;

    public CharacterAdapter(List<Character> listOfCharacter) {
        this.listOfCharacter = listOfCharacter;
        countOfHolders = listOfCharacter.size();
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_of_character;
        this.parent = parent;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return (new CharacterViewHolder(view, context));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.bind(listOfCharacter.get(position));
    }

    @Override
    public int getItemCount() {
        return countOfHolders;
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {

        TextView nameItemCharacter;
        ImageView imageItemCharacter;
        Context context;

        public CharacterViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            nameItemCharacter = itemView.findViewById(R.id.characterName);
            imageItemCharacter = itemView.findViewById(R.id.characterImage);
            this.context = context;
        }

        void bind(Character character) {

            String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
            nameItemCharacter.setText(character.getName());
            //imageItemCharacter.setImageUrl(character.getImageURL(), character.getImageLoader());
            Picasso.with(context).load(character.getImageURL()).into(imageItemCharacter);

        }
    }
}

