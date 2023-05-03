package com.example.therapyizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.therapyizer.R;
import com.example.therapyizer.interfaces.ScreeningResultInterface;
import com.example.therapyizer.models.QuestionsModel;

import java.util.ArrayList;

public class ScreeningNavAdapter extends RecyclerView.Adapter<ScreeningNavAdapter.MyViewHolder> {

    private final ScreeningResultInterface screeningResultInterface;

    Context context;
    ArrayList<QuestionsModel> questionsModels;

    public ScreeningNavAdapter(Context context, ArrayList<QuestionsModel> questionsModels, ScreeningResultInterface screeningResultInterface){
        this.context = context;
        this.questionsModels = questionsModels;
        this.screeningResultInterface = screeningResultInterface;
    }

    @NonNull
    @Override
    public ScreeningNavAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.patient_screeening_recycler_view_row, parent, false);
        return new ScreeningNavAdapter.MyViewHolder(view, screeningResultInterface);
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
        RadioGroup answerRadioGroup;
        RadioButton answerRadioButton;

        public MyViewHolder(@NonNull View itemView, ScreeningResultInterface screeningResultInterface) {
            super(itemView);

            questionsText = itemView.findViewById(R.id.questionText);

            answerRadioGroup = itemView.findViewById(R.id.patientScreeningRadioGroup);
            answerRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
                answerRadioButton = itemView.findViewById(i);

                if(screeningResultInterface != null){
                    int pos = getAbsoluteAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        screeningResultInterface.onItemClicked(pos, answerRadioButton.getText().toString());
                    }
                }

            });
        }

    }
}
