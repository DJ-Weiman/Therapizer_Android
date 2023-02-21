package com.example.therapyizer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.therapyizer.R;
import com.example.therapyizer.databinding.ActivityPatientRegistrationBinding;
import com.example.therapyizer.utilities.CurrentScreen;

public class PatientRegistrationActivity extends AppCompatActivity {

    ActivityPatientRegistrationBinding binding;
    private static CurrentScreen currentScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_registration);
        currentScreen = CurrentScreen.GENERAL_INFORMATION;

        binding.generalInformationMarker.setBackgroundColor(getColor(R.color.appThemePurple));
        binding.credentialsMarker.setBackgroundColor(getColor(R.color.unselected_gray));

        setupButtons();
    }

    private void setupButtons(){

        binding.topActionBar.backButton.setOnClickListener(view -> {
            if(currentScreen.equals(CurrentScreen.GENERAL_INFORMATION))
                onBackPressed();
            else if (currentScreen.equals(CurrentScreen.CREDENTIALS)) {
                currentScreen = CurrentScreen.GENERAL_INFORMATION;
                binding.credentialsMarker.setBackgroundColor(getColor(R.color.unselected_gray));
                binding.generalInformationMarker.setBackgroundColor(getColor(R.color.appThemePurple));
                if(binding.generalInformationLinearLayout.getVisibility() == View.GONE){
                    binding.credentialsLinearLayout.setVisibility(View.GONE);
                    binding.generalInformationLinearLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(currentScreen.equals(CurrentScreen.GENERAL_INFORMATION)){

                    currentScreen = CurrentScreen.CREDENTIALS;

                    binding.generalInformationMarker.setBackgroundColor(getColor(R.color.unselected_gray));
                    binding.credentialsMarker.setBackgroundColor(getColor(R.color.appThemePurple));

                    if(binding.credentialsLinearLayout.getVisibility() == View.GONE){
                        binding.generalInformationLinearLayout.setVisibility(View.GONE);
                        binding.credentialsLinearLayout.setVisibility(View.VISIBLE);
                    }
                }else if (currentScreen.equals(CurrentScreen.CREDENTIALS)){
                    Toast.makeText(PatientRegistrationActivity.this, "New Activity", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}

