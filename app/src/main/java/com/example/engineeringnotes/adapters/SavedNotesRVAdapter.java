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
import com.example.engineeringnotes.databases.savednotes.SavedNotes;

import org.w3c.dom.Text;

public class SavedNotesRVAdapter extends ListAdapter<SavedNotes,SavedNotesRVAdapter.SavedNotesHolder> {

    private Context context;
    private OnItemClickListener onItemClickListener;

    public SavedNotesRVAdapter(Context context, OnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    private static DiffUtil.ItemCallback<SavedNotes> DIFF_CALLBACK = new DiffUtil.ItemCallback<SavedNotes>() {
        @Override
        public boolean areItemsTheSame(@NonNull SavedNotes oldItem, @NonNull SavedNotes newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull SavedNotes oldItem, @NonNull SavedNotes newItem) {
            return oldItem.getChapterName().equals(newItem.getChapterName()) &&
                    oldItem.getLink().equals(newItem.getLink()) &&
                    oldItem.getDate().equals(newItem.getDate());
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
        SavedNotes note = getNoteAt(i);
        holder.chapterName.setText(note.getChapterName());
        holder.date.setText(note.getDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(note.getLink());
            }
        });

        holder.more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemIconClick(note,holder.more2);
            }
        });
    }

    public SavedNotes getNoteAt(int position){
        return getItem(position);
    }

    public class SavedNotesHolder extends RecyclerView.ViewHolder{
        TextView chapterName;
        CardView cardView;
        ImageView more2;
        TextView date;

        public SavedNotesHolder(@NonNull View itemView) {
            super(itemView);
            chapterName = itemView.findViewById(R.id.name);
            cardView = itemView.findViewById(R.id.cardview);
            more2 = itemView.findViewById(R.id.more2);
            date = itemView.findViewById(R.id.date);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String link);
        void onItemIconClick(SavedNotes note,ImageView view);
    }
}
