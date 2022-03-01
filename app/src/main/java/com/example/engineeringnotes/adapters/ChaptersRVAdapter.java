package com.example.engineeringnotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.engineeringnotes.R;

import java.util.List;

public class ChaptersRVAdapter extends RecyclerView.Adapter<ChaptersRVAdapter.ChapterViewHolder> {

    private List<String> chaptersList;
    private Context context;
    private OnChapterClickListener chapterClickListener;

    public ChaptersRVAdapter(List<String> chaptersList, Context context, OnChapterClickListener chapterClickListener) {
        this.chaptersList = chaptersList;
        this.context = context;
        this.chapterClickListener = chapterClickListener;
    }

    @NonNull
    @Override
    public ChaptersRVAdapter.ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item,parent,false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChaptersRVAdapter.ChapterViewHolder holder, int position) {
        String chapter = chaptersList.get(position);
        holder.chapterName.setText(chapter);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterClickListener.onChapterClick(chapter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chaptersList.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder{
        TextView chapterName;
        CardView cardView;
        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterName = itemView.findViewById(R.id.name);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }

    public interface OnChapterClickListener {
        void onChapterClick(String chapter);
    }
}


