package com.example.therapyizer.activities.patient;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.therapyizer.R;
import com.example.therapyizer.activities.database.UserGoalDatabase;
import com.example.therapyizer.adapters.PatientProgressAdapter;
import com.example.therapyizer.databinding.ActivityPatientProgressBinding;
import com.example.therapyizer.interfaces.PatientNavInterface;
import com.example.therapyizer.interfaces.UserGoalDao;
import com.example.therapyizer.models.PatientProgressGoalModel;
import com.example.therapyizer.models.User;
import com.example.therapyizer.models.UserGoal;

import java.util.ArrayList;
import java.util.List;

public class PatientProgressActivity extends AppCompatActivity {

    ActivityPatientProgressBinding binding;
    ArrayList<PatientProgressGoalModel> patientProgressGoalModels = new ArrayList<>();
    UserGoalDatabase db;
    List<UserGoal> userGoalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_patient_progress);

        db = Room.databaseBuilder(getApplicationContext(),
                        UserGoalDatabase.class,
                        "person-database")
                .allowMainThreadQueries()
                .build();

        getData();
        setUpSoberStreak();
        setPatientProgressGoalModels();
        setUpButtons();
    }

    private void setUpButtons() {
        PatientProgressAdapter adapter = new PatientProgressAdapter(this, patientProgressGoalModels);
        binding.patientProgressGoalsRV.setAdapter(adapter);
        binding.patientProgressGoalsRV.setLayoutManager(new LinearLayoutManager(this));
        binding.topActionBar.backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.trackerRewardButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PatientGoalRewardActivity.class);
            startActivity(intent);
        });

        binding.dailyUpdateButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PatientProgressUpdateActivity.class);
            intent.putExtra("PROGRESS", userGoalList.get(0).current_progress);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Log.d("valChecker", String.valueOf(data.getStringExtra("UPDATE_SELECTION")));

                if (data.getStringExtra("UPDATE_SELECTION").equals("Yes")) {
                    int current_progress = userGoalList.get(0).current_progress;
                    db.userGoalDao().updateProgress(1, current_progress + 1);
                }

                getData();
                setUpSoberStreak();
            }
        }
    }

    private void setPatientProgressGoalModels() {
        for (UserGoal goal : userGoalList) {
            if (goal.goalId == 1)
                continue;
            patientProgressGoalModels.add(new PatientProgressGoalModel(
                    goal.goalTitle,
                    String.valueOf(goal.goalTitle + " Progress: "),
                    goal.current_progress
            ));
        }
    }

    private void setUpSoberStreak() {
        UserGoal soberGoal = userGoalList.get(0);
        int currentProgress = soberGoal.current_progress;
        int daysToComplete = soberGoal.daysToComplete;

        binding.soberProgressCounter.setText(String.valueOf(currentProgress));
        binding.soberProgressBar.setMax(daysToComplete);
        binding.soberProgressBar.setProgressCompat(currentProgress, true);

        if (currentProgress == daysToComplete) {
            binding.trackerRewardButton.setVisibility(View.VISIBLE);
            binding.dailyUpdateButton.setVisibility(View.GONE);
        }
    }

    private void getData() {
        userGoalList = db.userGoalDao().getAllUSerGoals();
        if (userGoalList.isEmpty())
            insertGoals();
    }

    private void insertGoals() {
        UserGoal goal1 = new UserGoal("Sober streak", 10, 9);
        UserGoal goal2 = new UserGoal("Cycling", 10, 5);
        UserGoal goal3 = new UserGoal("Meditating", 15, 7);
        UserGoal goal4 = new UserGoal("Therapy", 12, 6);
        db.userGoalDao().insertAll(goal1, goal2, goal3, goal4);
    }
}