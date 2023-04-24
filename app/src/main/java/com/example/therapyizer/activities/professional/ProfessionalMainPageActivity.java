package com.example.therapyizer.activities.professional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.therapyizer.R;
import com.example.therapyizer.activities.patient.FindHelpActivity;
import com.example.therapyizer.activities.patient.KnowledgeHubActivity;
import com.example.therapyizer.activities.patient.PatientProgressActivity;
import com.example.therapyizer.activities.patient.WithdrawalActivity;
import com.example.therapyizer.adapters.PatientNavAdapter;
import com.example.therapyizer.databinding.ActivityProfessionalMainPageBinding;
import com.example.therapyizer.interfaces.PatientNavInterface;
import com.example.therapyizer.models.PatientNavCardModel;

import java.util.ArrayList;

public class ProfessionalMainPageActivity extends AppCompatActivity implements PatientNavInterface {

    ActivityProfessionalMainPageBinding binding;
    ArrayList<PatientNavCardModel> professionalNavCardModels = new ArrayList<>();
    int[] professionalNavImages = {
            R.drawable.img_professional_client,
            R.drawable.img_professional_notes,
            R.drawable.img_knowledgehub_books,
            R.drawable.therapy_icon
    };

    int[] professionalNavCardColors = {
            R.color.patient_withdrawal_green,
            R.color.patient_find_help_purple,
            R.color.patient_therapy_orange,
            R.color.patient_knowledgehub_blue
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_professional_main_page);

        setUpProfessionalNavCardModel();

        PatientNavAdapter adapter = new PatientNavAdapter(this, professionalNavCardModels, this);
        binding.professionalNavRecyclerView.setAdapter(adapter);
        binding.professionalNavRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());
    }

    private void setUpProfessionalNavCardModel(){
        String[] navTitles = getResources().getStringArray(R.array.professional_nav_card_titles);
        String[] navTexts = getResources().getStringArray(R.array.professional_nav_card_descriptions);

        for (int i = 0; i < navTitles.length; i++) {
            professionalNavCardModels.add(new PatientNavCardModel(navTitles[i], navTexts[i], professionalNavImages[i], professionalNavCardColors[i]));
        }
    }


    @Override
    public void onItemClick(int position) {
        Intent clientsIntent = new Intent(getApplicationContext(), ProfessionalClientsActivity.class);
        Intent notesIntent = new Intent(getApplicationContext(), ProfessionalNotesActivity.class);
        Intent knowledgeHubIntent = new Intent(getApplicationContext(), KnowledgeHubActivity.class);
        Intent appointmentsIntent = new Intent(getApplicationContext(), ProfessionalAppointmentsActivity.class);

        switch (position){
            case 0:
                startActivity(clientsIntent);
                break;
            case 1:
                startActivity(notesIntent);
                break;
            case 2:
                startActivity(knowledgeHubIntent);
                break;
            case 3:
                startActivity(appointmentsIntent);
                break;
            default:
                startActivity(clientsIntent);
        }
    }
}