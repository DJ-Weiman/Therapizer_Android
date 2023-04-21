package com.example.therapyizer.activities.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.therapyizer.R;
import com.example.therapyizer.adapters.PatientNavAdapter;
import com.example.therapyizer.databinding.ActivityPatientMainPageBinding;
import com.example.therapyizer.interfaces.PatientNavInterface;
import com.example.therapyizer.models.PatientNavCardModel;

import java.util.ArrayList;

public class PatientMainPageActivity extends AppCompatActivity implements PatientNavInterface {

    ActivityPatientMainPageBinding binding;
    ArrayList<PatientNavCardModel> patientNavCardModels = new ArrayList<>();
    int[] patientNavImages = {R.drawable.patient_progress_icon,
            R.drawable.knowledge_hub_icon,
            R.drawable.withdrawal_icon,
            R.drawable.find_help_icon,
            R.drawable.therapy_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_main_page);

        setUpPatientNavCardModels();

        PatientNavAdapter adapter = new PatientNavAdapter(this, patientNavCardModels, this);
        binding.patientNavRecyclerView.setAdapter(adapter);
        binding.patientNavRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.topActionBar.backButton.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void setUpPatientNavCardModels() {
        String[] navTitles = getResources().getStringArray(R.array.patient_nav_titles);
        String[] navTexts = getResources().getStringArray(R.array.patient_nav_text);

        for (int i = 0; i < navTitles.length; i++) {
            patientNavCardModels.add(new PatientNavCardModel(navTitles[i], navTexts[i], patientNavImages[i]));
        }
    }

    @Override
    public void onItemClick(int position) {

        Intent progressIntent = new Intent(getApplicationContext(), PatientProgressActivity.class);
        Intent knowledgeHubIntent = new Intent(getApplicationContext(), KnowledgeHubActivity.class);
        Intent withdrawalIntent = new Intent(getApplicationContext(), WithdrawalActivity.class);
        Intent findHelpIntent = new Intent(getApplicationContext(), FindHelpActivity.class);

        switch (position){
            case 0:
                startActivity(progressIntent);
                break;
            case 1:
                startActivity(knowledgeHubIntent);
                break;
            case 2:
                startActivity(withdrawalIntent);
                break;
            case 3:
                startActivity(findHelpIntent);
                break;
            default:
                startActivity(progressIntent);
        }

    }
}