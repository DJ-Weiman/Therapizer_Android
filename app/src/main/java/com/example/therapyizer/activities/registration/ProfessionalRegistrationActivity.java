package com.example.therapyizer.activities.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.therapyizer.R;
import com.example.therapyizer.databinding.ActivityProfessionalRegistrationBinding;
import com.example.therapyizer.utilities.CurrentScreen;

public class ProfessionalRegistrationActivity extends AppCompatActivity {

    ActivityProfessionalRegistrationBinding binding;
    private static CurrentScreen currentScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_professional_registration);
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
                if(binding.generalInformationScrollView.getVisibility() == View.GONE){
                    binding.credentialsScrollView.setVisibility(View.GONE);
                    binding.generalInformationScrollView.setVisibility(View.VISIBLE);
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

                    if(binding.credentialsScrollView.getVisibility() == View.GONE){
                        binding.generalInformationScrollView.setVisibility(View.GONE);
                        binding.credentialsScrollView.setVisibility(View.VISIBLE);
                    }
                }else if (currentScreen.equals(CurrentScreen.CREDENTIALS)){
                    Toast.makeText(ProfessionalRegistrationActivity.this, "New Activity", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}