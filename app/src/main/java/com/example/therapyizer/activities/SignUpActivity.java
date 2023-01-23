package com.example.therapyizer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.therapyizer.R;
import com.example.therapyizer.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        binding.imageBack.setOnClickListener(view -> onBackPressed());
        binding.signInText.setOnClickListener(view -> onBackPressed());
    }
}