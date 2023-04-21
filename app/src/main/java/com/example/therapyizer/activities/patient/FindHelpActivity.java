package com.example.therapyizer.activities.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.therapyizer.R;
import com.example.therapyizer.databinding.ActivityFindHelpBinding;

public class FindHelpActivity extends AppCompatActivity {

    ActivityFindHelpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_help);

        binding.searchButton.setBackgroundColor(getColor(R.color.search_button_grey));
        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());
    }
}