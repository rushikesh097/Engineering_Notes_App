package com.example.engineeringnotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.engineeringnotes.R;

import java.util.List;

public class SavedNotesRVAdapter extends ListAdapter<String,SavedNotesRVAdapter.SavedNotesHolder> {

    private Context context;
    private OnItemClickListener onItemClickListener;

    public SavedNotesRVAdapter(Context context, OnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    private static final DiffUtil.ItemCallback<String> DIFF_CALLBACK = new DiffUtil.ItemCallback<String>() {
        @Override
        public boolean areItemsTheSame(@NonNull String s, @NonNull String t1) {
            return s.equals(t1);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String s, @NonNull String t1) {
            return s.equals(t1);
        }
    };

    @NonNull
    @Override
    public SavedNotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_saved_notes,parent,false);
        return new SavedNotesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedNotesHolder holder, int i) {
        String chapter = getChapterNameAt(i);
        holder.chapterName.setText(chapter);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(chapter);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemIconClick(chapter);
            }
        });
    }

    public String getChapterNameAt(int position){
        return getItem(position);
    }

    public class SavedNotesHolder extends RecyclerView.ViewHolder{
        TextView chapterName;
        CardView cardView;
        ImageView delete;
        public SavedNotesHolder(@NonNull View itemView) {
            super(itemView);
            chapterName = itemView.findViewById(R.id.name);
            cardView = itemView.findViewById(R.id.cardview);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String chapter);
        void onItemIconClick(String chapter);
    }
}
