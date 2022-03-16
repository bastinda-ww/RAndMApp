package com.example.randmapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randmapp.model.Character;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<Character> listOfCharacter;

    private ViewGroup parent;
    private OnCharacterClickListener onCharacterClickListener;

    public CharacterAdapter(List<Character> listOfCharacter, OnCharacterClickListener onCharacterClickListener) {
        this.listOfCharacter = listOfCharacter;
        this.onCharacterClickListener = onCharacterClickListener;
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
        if (listOfCharacter == null)
            return 0;
        return listOfCharacter.size();
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {

        TextView nameItemCharacter;
        ImageView imageItemCharacter;
        Context context;

        public CharacterViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            nameItemCharacter = itemView.findViewById(R.id.charName);
            imageItemCharacter = itemView.findViewById(R.id.characterImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Character character = listOfCharacter.get(getLayoutPosition());
                    onCharacterClickListener.onCharacterClick(character);
                }
            });
        }

        void bind(Character character) {
            nameItemCharacter.setText(character.getName());
            Picasso.with(context).load(character.getImage()).into(imageItemCharacter);

        }
    }

    public interface OnCharacterClickListener {
        void onCharacterClick(Character character);
    }
}

