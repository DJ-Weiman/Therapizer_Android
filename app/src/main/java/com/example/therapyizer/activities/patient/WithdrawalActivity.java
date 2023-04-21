package com.example.therapyizer.activities.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.therapyizer.R;
import com.example.therapyizer.databinding.ActivityWithdrawalBinding;

public class WithdrawalActivity extends AppCompatActivity {

    ActivityWithdrawalBinding binding;
    String[] symptomList = {"Depression", "Trembling and tremors", "Vomiting", "Hunger or loss of appetite", "Insomnia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_withdrawal);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_symptom_list_item, R.id.symptomText, symptomList);

        binding.withdrawalSymptomsListView.setAdapter(arrayAdapter);
        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());
    }
}