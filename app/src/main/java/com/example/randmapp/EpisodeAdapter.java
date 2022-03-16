package com.example.randmapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randmapp.model.Episode;

import java.util.ArrayList;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    private List<Episode> listOfEpisode;

    public EpisodeAdapter(List<Episode> listOfEpisode) {
        this.listOfEpisode = listOfEpisode;
    }

    // method for filtering our recyclerview items.
    public void filterList(List<Episode> filterListOfEpisode) {
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
        holder.nameOfEpisode.setText(listOfEpisode.get(position).getName());
        holder.airDateOfEpisode.setText(listOfEpisode.get(position).getAirDate());

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
