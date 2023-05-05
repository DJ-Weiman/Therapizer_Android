package com.example.therapyizer.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.therapyizer.R;
import com.example.therapyizer.models.PatientNavCardModel;
import com.example.therapyizer.models.PatientProgressGoalModel;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;

public class PatientProgressAdapter extends RecyclerView.Adapter<PatientProgressAdapter.MyViewHolder> {

    Context context;
    ArrayList<PatientProgressGoalModel> patientProgressGoalModels;

    public PatientProgressAdapter(Context context, ArrayList<PatientProgressGoalModel> patientProgressGoalModels) {
        this.context = context;
        this.patientProgressGoalModels = patientProgressGoalModels;
    }

    @NonNull
    @Override
    public PatientProgressAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.patient_progress_goal_recycler_view_row, parent, false);
        return new PatientProgressAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientProgressAdapter.MyViewHolder holder, int position) {
        holder.goalTitle.setText(patientProgressGoalModels.get(position).getGoalTitle());
        holder.goalText.setText(patientProgressGoalModels.get(position).getGoalText());
        holder.goalProgress.setText(String.valueOf(patientProgressGoalModels.get(position).getProgressDays()));
        holder.progressIndicator.setProgressCompat(patientProgressGoalModels.get(position).getProgressDays(), true);

    }

    @Override
    public int getItemCount() {
        return patientProgressGoalModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView goalTitle, goalText, goalProgress;
        LinearProgressIndicator progressIndicator;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            goalTitle = itemView.findViewById(R.id.goalTitleText);
            goalText = itemView.findViewById(R.id.goalEncouragementText);
            goalProgress = itemView.findViewById(R.id.goalProgressCounter);
            progressIndicator = itemView.findViewById(R.id.goalProgressTracker);

        }
    }
}
