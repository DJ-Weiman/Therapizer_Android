package com.example.therapyizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.therapyizer.databinding.ActivityMainBinding;
import com.example.therapyizer.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);

        binding.signUpText.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));

    }
}