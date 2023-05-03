package com.example.therapyizer.activities.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.therapyizer.R;
import com.example.therapyizer.activities.patient.PatientMainPageActivity;
import com.example.therapyizer.databinding.ActivityPatientScreeningResultBinding;

public class PatientScreeningResultActivity extends AppCompatActivity {

    ActivityPatientScreeningResultBinding binding;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_screening_result);
        extras = getIntent().getExtras();

        setScore();

        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());

        binding.nextButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PatientMainPageActivity.class);
            startActivity(intent);
        });
    }

    private void setScore(){
        int score = (int) extras.get("NUM_POSITIVES");

        binding.screeningNumericalResult.setText(String.valueOf(score + " out of 6"));
        binding.screeningResultProgressBar.setIndicatorColor(getResources().getColor(R.color.appThemePurple));
        binding.screeningResultProgressBar.setProgressCompat(score, true);

        if(score <= 2)
            binding.screeningResultText.setText(String.valueOf("Mild"));
        else if (score <= 4)
            binding.screeningResultText.setText(String.valueOf("Medium"));
        else
            binding.screeningResultText.setText(String.valueOf("Extreme"));
    }
}