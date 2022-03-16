package com.example.randmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.PageViewHolder> {
    private int numberOfPage;
    private int page;
    private ViewGroup parent;
    private OnPageClickListener onPageClickListener;

//    private final API characterAPI = RestService.getData();
//    private void loadData(int page) {
//        characterAPI.getData(page).enqueue(new Callback<ResponseCharactersDTO>() {
//            @Override
//            public void onResponse(Call<ResponseCharactersDTO> call, Response<ResponseCharactersDTO> response) {
//                if (response.isSuccessful()) {
//                    listOfCharacter = response.body().getResults();
//                    characterAdapter = new CharacterAdapter(listOfCharacter);
//                    characterRecyclerView.setAdapter(characterAdapter);
//                    pageAdapter = new PageAdapter(10);
//                    pageRecyclerView.setAdapter(pageAdapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseCharactersDTO> call, Throwable t) {
//                System.out.println("Throwable " + t);
//            }
//        });
//    }

    public PageAdapter(int numberOfPage, OnPageClickListener onPageClickListener) {
        this.numberOfPage = numberOfPage;
        this.onPageClickListener = onPageClickListener;
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
        return numberOfPage;
    }

    class PageViewHolder extends RecyclerView.ViewHolder {
        TextView numberOfPage;

        public PageViewHolder(@NonNull View itemView) {
            super(itemView);
            numberOfPage = itemView.findViewById(R.id.numberOfPage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int page = getLayoutPosition();
                    onPageClickListener.onPageClick(page + 1);
                }
            });
        }

        public void bind(int counter) {
            numberOfPage.setText(String.valueOf(counter));
        }
    }

    public interface OnPageClickListener {
        void onPageClick(int page);
    }

}
