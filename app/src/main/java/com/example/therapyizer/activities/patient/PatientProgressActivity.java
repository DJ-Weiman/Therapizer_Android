package com.example.therapyizer.activities.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.therapyizer.R;
import com.example.therapyizer.adapters.PatientProgressAdapter;
import com.example.therapyizer.databinding.ActivityPatientProgressBinding;
import com.example.therapyizer.interfaces.PatientNavInterface;
import com.example.therapyizer.models.PatientProgressGoalModel;

import java.util.ArrayList;

public class PatientProgressActivity extends AppCompatActivity {

    ActivityPatientProgressBinding binding;
    ArrayList<PatientProgressGoalModel> patientProgressGoalModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_progress);

        setPatientProgressGoalModels();

        PatientProgressAdapter adapter = new PatientProgressAdapter(this, patientProgressGoalModels);
        binding.patientProgressGoalsRV.setAdapter(adapter);
        binding.patientProgressGoalsRV.setLayoutManager(new LinearLayoutManager(this));
        binding.topActionBar.backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.trackerRewardButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PatientGoalRewardActivity.class);
            startActivity(intent);
        });
    }

    private void setPatientProgressGoalModels(){

        String[] goalTitles = {"Cycling", "Meditating", "Therapy"};
        String[] goalText = {
                "You have been Cycling for",
                "You have been Meditating for",
                "You have been going to Therapy for"};
        int[] goalProgressDays = {12, 20, 18};

        for(int i=0; i<goalTitles.length;i++){
            patientProgressGoalModels.add(new PatientProgressGoalModel(
                    goalTitles[i],
                    goalText[i],
                    goalProgressDays[i]
            ));
        }

    }
}