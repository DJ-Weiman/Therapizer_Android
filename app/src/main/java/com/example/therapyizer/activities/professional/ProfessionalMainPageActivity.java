package com.example.therapyizer.activities.professional;

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
import com.example.therapyizer.activities.patient.FindHelpActivity;
import com.example.therapyizer.activities.patient.KnowledgeHubActivity;
import com.example.therapyizer.activities.patient.PatientProgressActivity;
import com.example.therapyizer.activities.patient.WithdrawalActivity;
import com.example.therapyizer.adapters.PatientNavAdapter;
import com.example.therapyizer.databinding.ActivityProfessionalMainPageBinding;
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

public class ProfessionalMainPageActivity extends AppCompatActivity implements PatientNavInterface {

    ActivityProfessionalMainPageBinding binding;
    private PreferenceManager preferenceManager;
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
        preferenceManager = new PreferenceManager(getApplicationContext());

        setUpProfessionalNavCardModel();

        PatientNavAdapter adapter = new PatientNavAdapter(this, professionalNavCardModels, this);
        binding.professionalNavRecyclerView.setAdapter(adapter);
        binding.professionalNavRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());
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
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ProfessionalMainPageActivity.this, "Token updated sucessfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ProfessionalMainPageActivity.this, "Unable to send Token: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("fcmTokenChecker", "sendFCMTokenToDatabase: " + e.getMessage());
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
                .addOnFailureListener(e -> Toast.makeText(ProfessionalMainPageActivity.this, "There was an issue Signing out", Toast.LENGTH_SHORT).show());
    }

    private void setUpProfessionalNavCardModel() {
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

        switch (position) {
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