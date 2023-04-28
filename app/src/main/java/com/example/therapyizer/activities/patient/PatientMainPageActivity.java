package com.example.therapyizer.activities.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.therapyizer.R;
import com.example.therapyizer.activities.MainActivity;
import com.example.therapyizer.activities.SignInActivity;
import com.example.therapyizer.activities.professional.ProfessionalMainPageActivity;
import com.example.therapyizer.adapters.PatientNavAdapter;
import com.example.therapyizer.databinding.ActivityPatientMainPageBinding;
import com.example.therapyizer.interfaces.PatientNavInterface;
import com.example.therapyizer.models.PatientNavCardModel;
import com.example.therapyizer.utilities.Constants;
import com.example.therapyizer.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;

public class PatientMainPageActivity extends AppCompatActivity implements PatientNavInterface {

    ActivityPatientMainPageBinding binding;
    private PreferenceManager preferenceManager;
    ArrayList<PatientNavCardModel> patientNavCardModels = new ArrayList<>();
    int[] patientNavImages = {
            R.drawable.patient_progress_icon,
            R.drawable.knowledge_hub_icon,
            R.drawable.withdrawal_icon,
            R.drawable.find_help_icon,
            R.drawable.therapy_icon};

    int[] patientNavCardColors = {
            R.color.patient_progress_purple,
            R.color.patient_knowledgehub_blue,
            R.color.patient_withdrawal_green,
            R.color.patient_find_help_purple,
            R.color.patient_therapy_orange,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_main_page);
        preferenceManager = new PreferenceManager(getApplicationContext());

        setUpPatientNavCardModels();

        PatientNavAdapter adapter = new PatientNavAdapter(this, patientNavCardModels, this);
        binding.patientNavRecyclerView.setAdapter(adapter);
        binding.patientNavRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.topActionBar.backButton.setOnClickListener(view -> {
            onBackPressed();
        });
        binding.signOutButton.setOnClickListener(view -> signOut());
        binding.userWelcomeText.setText("Hello " + preferenceManager.getString(Constants.KEY_FIRST_NAME));

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                sendFCMTokenToDatabase(task.getResult());
            }
        });
    }

    private void sendFCMTokenToDatabase(String token) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        documentReference.update(Constants.KEY_FCM_TOKEN, token)
                .addOnFailureListener(e -> {
                    Toast.makeText(PatientMainPageActivity.this, "Unable to send Token: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }

    private void signOut() {
        Toast.makeText(this, "Signing out...", Toast.LENGTH_SHORT).show();
        FirebaseFirestore database = FirebaseFirestore.getInstance();

        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );

        HashMap<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    preferenceManager.clearPreferences();
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(PatientMainPageActivity.this, "There was an issue Signing out", Toast.LENGTH_SHORT).show());
    }

    private void setUpPatientNavCardModels() {
        String[] navTitles = getResources().getStringArray(R.array.patient_nav_titles);
        String[] navTexts = getResources().getStringArray(R.array.patient_nav_text);

        for (int i = 0; i < navTitles.length; i++) {
            patientNavCardModels.add(new PatientNavCardModel(navTitles[i], navTexts[i], patientNavImages[i], patientNavCardColors[i]));
        }
    }

    @Override
    public void onItemClick(int position) {

        Intent progressIntent = new Intent(getApplicationContext(), PatientProgressActivity.class);
        Intent knowledgeHubIntent = new Intent(getApplicationContext(), KnowledgeHubActivity.class);
        Intent withdrawalIntent = new Intent(getApplicationContext(), WithdrawalActivity.class);
        Intent findHelpIntent = new Intent(getApplicationContext(), FindHelpActivity.class);
        Intent therapyIntent = new Intent(getApplicationContext(), TherapyActivity.class);

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
            case 4:
                startActivity(therapyIntent);
                break;
            default:
                startActivity(progressIntent);
        }

    }
}