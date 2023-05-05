package com.example.therapyizer.activities.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.therapyizer.R;
import com.example.therapyizer.activities.patient.PatientMainPageActivity;
import com.example.therapyizer.adapters.NotesNavAdapter;
import com.example.therapyizer.adapters.ScreeningNavAdapter;
import com.example.therapyizer.databinding.ActivityPatientScreeeningBinding;
import com.example.therapyizer.interfaces.ScreeningResultInterface;
import com.example.therapyizer.models.NotesModel;
import com.example.therapyizer.models.QuestionsModel;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class PatientScreeeningActivity extends AppCompatActivity implements ScreeningResultInterface {

    ArrayList<QuestionsModel> questionsModels = new ArrayList<>();
    ActivityPatientScreeeningBinding binding;
    ArrayList<String> answerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_screeening);
        binding.topActionBar.backButton.setVisibility(View.GONE);

        binding.nextButton.setOnClickListener(view -> {
            if (answerList.contains("Blank")) {
                Toast.makeText(this, "Please Make sure you have answered all the above questions", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getApplicationContext(), PatientScreeningResultActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                int numberOfPositives = 0;
                for(int i=0; i< answerList.size(); i++){
                    if(answerList.get(i).equals("Yes"))
                        numberOfPositives++;
                }
                intent.putExtra("NUM_POSITIVES", numberOfPositives);
                startActivity(intent);
            }
        });

        setQuestionsModels();
        setupAnswerList();

        ScreeningNavAdapter adapter = new ScreeningNavAdapter(this, questionsModels, this);
        binding.screeningRecyclerView.setAdapter(adapter);
        binding.screeningRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupAnswerList() {
        for (int i = 0; i < 6; i++) {
            answerList.add("Blank");
        }
    }

    private void setQuestionsModels() {
        String[] questionsText = getResources().getStringArray(R.array.screening_questions);

        for (int i = 0; i < questionsText.length; i++) {
            questionsModels.add(new QuestionsModel(questionsText[i]));
        }
    }

    @Override
    public void onItemClicked(int position, String result) {
        answerList.set(position, result);
    }
}