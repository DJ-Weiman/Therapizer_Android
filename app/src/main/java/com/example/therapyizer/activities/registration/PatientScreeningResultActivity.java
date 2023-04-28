package com.example.therapyizer.activities.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.therapyizer.R;
import com.example.therapyizer.activities.patient.PatientMainPageActivity;
import com.example.therapyizer.databinding.ActivityPatientScreeningResultBinding;

public class PatientScreeningResultActivity extends AppCompatActivity {

    ActivityPatientScreeningResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_screening_result);

        binding.topActionBar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PatientMainPageActivity.class);
                startActivity(intent);
            }
        });
    }
}