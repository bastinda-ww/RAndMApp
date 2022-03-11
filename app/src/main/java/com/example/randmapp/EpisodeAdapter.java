package com.example.randmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randmapp.model.Episode;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    private ArrayList<Episode> listOfEpisode;
    private Context context;

    public EpisodeAdapter(ArrayList<Episode> listOfEpisode, Context context) {
        this.listOfEpisode = listOfEpisode;
        this.context = context;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<Episode> filterListOfEpisode) {
        // below line is to add our filtered
        // list in our course array list.
        listOfEpisode = filterListOfEpisode;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_episode, parent, false);
        return (new EpisodeViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        Episode episode = listOfEpisode.get(position);
        holder.nameOfEpisode.setText(episode.getName());
        holder.airDateOfEpisode.setText(episode.getAirDate());

    }

    @Override
    public int getItemCount() {
        return listOfEpisode.size();
    }

    class EpisodeViewHolder extends RecyclerView.ViewHolder {

        private TextView nameOfEpisode, airDateOfEpisode;

        public EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfEpisode = itemView.findViewById(R.id.nameOfEpisode);
            airDateOfEpisode = itemView.findViewById(R.id.airDateOfEpisode);
        }
    }
}
