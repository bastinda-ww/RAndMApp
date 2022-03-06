package com.example.randmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.PageViewHolder> {
    private int countOfPage;
    private int counter;
    private ViewGroup parent;

    public PageAdapter(int countOfPage) {
        this.countOfPage = countOfPage;
        counter = 0;
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        this.parent = parent;
        int layoutIdForItemPage = R.layout.item_of_page;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForItemPage, parent, false);
        PageViewHolder pageViewHolder = new PageViewHolder(view);
        pageViewHolder.numberOfPage.setText(counter);
        counter++;
        return pageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PageAdapter.PageViewHolder holder, int position) {
        holder.bind();
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
        }

        public void bind() {
            numberOfPage.setText(counter);

        }
    }
}
