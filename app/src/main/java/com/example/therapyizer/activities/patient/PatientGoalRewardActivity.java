package com.example.therapyizer.activities.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.therapyizer.R;
import com.example.therapyizer.databinding.ActivityPatientGoalRewardBinding;

public class PatientGoalRewardActivity extends AppCompatActivity {

    ActivityPatientGoalRewardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_goal_reward);

        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());
    }
}