package com.example.engineeringnotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.engineeringnotes.R;

import org.w3c.dom.Text;

import java.util.List;

public class ChaptersRVAdapter extends RecyclerView.Adapter<ChaptersRVAdapter.ChapterViewHolder> {

    private List<String> chaptersList;
    private Context context;
    private String subject;
    private OnChapterClickListener chapterClickListener;

    public ChaptersRVAdapter(List<String> chaptersList, Context context, String subject, OnChapterClickListener chapterClickListener) {
        this.chaptersList = chaptersList;
        this.context = context;
        this.subject = subject;
        this.chapterClickListener = chapterClickListener;
    }

    @NonNull
    @Override
    public ChaptersRVAdapter.ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_chapters,parent,false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChaptersRVAdapter.ChapterViewHolder holder, int position) {
        String chapter = chaptersList.get(position);
        holder.chapterName.setText(chapter);
        if(chapter.equals("Question Papers") || chapter.contains("Syllabus")){
            holder.chapterNo.setText("");
        }
        else{
            holder.chapterNo.setText(holder.chapterNo.getText().toString().concat(String.valueOf(position+1)));
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterClickListener.onChapterClick(chapter);
            }
        });

        holder.more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                chapterClickListener.onPopupMenuClick(chapter, holder.more1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chaptersList.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder{
        TextView chapterName;
        TextView chapterNo;
        CardView cardView;
        ImageView more1;
        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterName = itemView.findViewById(R.id.name);
            cardView = itemView.findViewById(R.id.cardview);
            more1 = itemView.findViewById(R.id.more1);
            chapterNo = itemView.findViewById(R.id.chapter_no);
        }
    }

    public interface OnChapterClickListener {
        void onChapterClick(String chapter);
        void onPopupMenuClick(String chapter, ImageView view);
    }
}


