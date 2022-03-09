package com.example.engineeringnotes.adapters;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.engineeringnotes.ChaptersActivity;
import com.example.engineeringnotes.R;
import com.example.engineeringnotes.databases.SubjectNotesViewModel;;

import java.util.List;

public class SubjectsRVAdapter extends RecyclerView.Adapter<SubjectsRVAdapter.SubjectViewHolder> {

    private List<String >subjectList;
    private Context context;
    private Application application;
    private int semester;

    public SubjectsRVAdapter(List<String> subjectList, Context context, Application application, int semester) {
        this.subjectList = subjectList;
        this.context = context;
        this.application = application;
        this.semester = semester;
    }

    @NonNull
    @Override
    public SubjectsRVAdapter.SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_subjects,parent,false);
        return new SubjectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectsRVAdapter.SubjectViewHolder holder, int position) {
        String subject = subjectList.get(position);
        holder.subjectName.setText(subject);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!subject.equals("Marking Scheme")){
                    Intent intent = new Intent(context, ChaptersActivity.class);
                    intent.putExtra("subject_name",subject);
                    context.startActivity(intent);
                }
                else{
                    String link = new SubjectNotesViewModel(application)
                            .getLinkFromSemester(semester,subject)
                            .get(0);
                    openWebPage(link);
                }
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

    @SuppressLint("QueryPermissionsNeeded")
    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        try{
            context.startActivity(intent);
        }catch (Exception e){
            Toast.makeText(context, "Error !", Toast.LENGTH_SHORT).show();
        }
    }


}