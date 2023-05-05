package com.example.therapyizer.activities.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.therapyizer.R;
import com.example.therapyizer.databinding.ActivityPatientProgressUpdateBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PatientProgressUpdateActivity extends AppCompatActivity {

    ActivityPatientProgressUpdateBinding binding;
    String updateSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_progress_update);

        binding.patientProgressRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton answer = findViewById(i);
            updateSelection = answer.getText().toString();
        });

        binding.topActionBar.backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        Bundle extras = getIntent().getExtras();
        int progress = (int) extras.get("PROGRESS");

        binding.soberProgressBar.setProgressCompat(progress, true);
        binding.soberProgressCounter.setText(String.valueOf(progress));

        binding.progressUpdateSaveButton.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("UPDATE_SELECTION", updateSelection);
            setResult(RESULT_OK, intent);
            finish();
        });

        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE.LLLL.yyyy KK:mm:ss aaa z");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd LLLL yyyy, EEEE");
        String dateTime = simpleDateFormat.format(calendar.getTime()).toString();
        binding.ProgressDateText.setText(dateTime);

    }
}