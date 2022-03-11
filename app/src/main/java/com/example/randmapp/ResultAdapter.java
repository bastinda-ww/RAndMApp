package com.example.randmapp;

import android.content.Context;
import android.os.Build;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.randmapp.api.CharacterAPI;
import com.example.randmapp.model.Character;
import com.example.randmapp.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private List<Result> posts;

    public ResultAdapter(List<Result> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_character, parent, false);
        return new ViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameItemCharacter;
        ImageView imageItemCharacter;
        Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            nameItemCharacter = itemView.findViewById(R.id.charName);
            imageItemCharacter = itemView.findViewById(R.id.characterImage);
        }

        void bind(Result result) {
            nameItemCharacter.setText(result.getName());
            Picasso.with(context).load(result.getImage()).into(imageItemCharacter);

        }
    }
}