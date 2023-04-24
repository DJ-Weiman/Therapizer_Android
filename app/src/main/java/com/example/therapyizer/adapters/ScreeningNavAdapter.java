package com.example.therapyizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.therapyizer.R;
import com.example.therapyizer.models.QuestionsModel;

import java.util.ArrayList;

public class ScreeningNavAdapter extends RecyclerView.Adapter<ScreeningNavAdapter.MyViewHolder> {

    Context context;
    ArrayList<QuestionsModel> questionsModels;

    public ScreeningNavAdapter(Context context, ArrayList<QuestionsModel> questionsModels){
        this.context = context;
        this.questionsModels = questionsModels;
    }

    @NonNull
    @Override
    public ScreeningNavAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.patient_screeening_recycler_view_row, parent, false);
        return new ScreeningNavAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScreeningNavAdapter.MyViewHolder holder, int position) {
        holder.questionsText.setText(questionsModels.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return questionsModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView questionsText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            questionsText = itemView.findViewById(R.id.questionText);
        }

    }
}
