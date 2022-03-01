package com.example.engineeringnotes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.engineeringnotes.ChaptersActivity;
import com.example.engineeringnotes.R;

import java.util.List;

public class SubjectsRVAdapter extends RecyclerView.Adapter<SubjectsRVAdapter.SubjectViewHolder> {

    private List<String >subjectList;
    private Context context;


    public SubjectsRVAdapter(List<String> subjectList, Context context) {
        this.subjectList = subjectList;
        this.context = context;
    }

    @NonNull
    @Override
    public SubjectsRVAdapter.SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new SubjectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectsRVAdapter.SubjectViewHolder holder, int position) {
        String subject = subjectList.get(position);
        holder.subjectName.setText(subject);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChaptersActivity.class);
                intent.putExtra("subject_name",subject);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView subjectName;
        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectName = itemView.findViewById(R.id.name);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }


}