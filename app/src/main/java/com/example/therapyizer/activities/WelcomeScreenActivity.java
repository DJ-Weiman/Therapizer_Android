package com.example.therapyizer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.therapyizer.R;
import com.example.therapyizer.activities.patient.PatientMainPageActivity;
import com.example.therapyizer.activities.patient.PatientProgressActivity;
import com.example.therapyizer.activities.registration.UserTypeSelectionActivity;
import com.example.therapyizer.databinding.ActivityWelcomeScreenBinding;

public class WelcomeScreenActivity extends AppCompatActivity {

    ActivityWelcomeScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome_screen);

        setupButtons();
    }

    private void setupButtons(){
        binding.loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PatientMainPageActivity.class);
            startActivity(intent);
        });

        binding.registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), UserTypeSelectionActivity.class);
            startActivity(intent);
        });
    }
}