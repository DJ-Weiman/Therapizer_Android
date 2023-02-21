package com.example.therapyizer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.therapyizer.R;
import com.example.therapyizer.databinding.ActivityUserTypeSelectionBinding;

public class UserTypeSelectionActivity extends AppCompatActivity {

    ActivityUserTypeSelectionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_type_selection);

        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());

        binding.patientContinueButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PatientRegistrationActivity.class);
            startActivity(intent);
        });

        binding.professionalContinueButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ProfessionalRegistrationActivity.class);
            startActivity(intent);
        });
    }
}