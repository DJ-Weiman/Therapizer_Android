package com.example.therapyizer.activities.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.therapyizer.R;
import com.example.therapyizer.activities.OutgoingInvitationActivity;
import com.example.therapyizer.adapters.OnlineCallNavAdapter;
import com.example.therapyizer.databinding.ActivityTherapyBinding;
import com.example.therapyizer.interfaces.OnlineCallInterface;
import com.example.therapyizer.interfaces.PatientNavInterface;
import com.example.therapyizer.models.OnlineCallModel;
import com.example.therapyizer.utilities.Constants;
import com.example.therapyizer.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class TherapyActivity extends AppCompatActivity implements OnlineCallInterface {

    ActivityTherapyBinding binding;
    ArrayList<OnlineCallModel> onlineCallModels = new ArrayList<>();
    PreferenceManager preferenceManager;
    OnlineCallNavAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(TherapyActivity.this, R.layout.activity_therapy);
        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());
        preferenceManager = new PreferenceManager(getApplicationContext());


        adapter = new OnlineCallNavAdapter(this, this, onlineCallModels);
        binding.therapyRecyclerView.setAdapter(adapter);
        binding.therapyRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        Log.d("tokenChecker", "aksdbnkaskdn");

        setUpOnlineCallModels();
    }


    private void setUpOnlineCallModels(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .get()
                .addOnCompleteListener(task -> {
                    String myUserId = preferenceManager.getString(Constants.KEY_USER_ID);
                    if(task.isSuccessful() && task.getResult() != null){
                        for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                            if(documentSnapshot.getString(Constants.KEY_USER_TYPE).equals(Constants.PROFESSIONAL_USER_TYPE)){
                                onlineCallModels.add(new OnlineCallModel(
                                        documentSnapshot.getId(),
                                        documentSnapshot.getString(Constants.KEY_FIRST_NAME) + " " + documentSnapshot.getString(Constants.KEY_LAST_NAME),
                                        documentSnapshot.getString(Constants.KEY_PROFESSION),
                                        documentSnapshot.getString(Constants.KEY_FCM_TOKEN)
                                ));
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    @Override
    public void onCallClicked(OnlineCallModel onlineCallModel) {

        Log.d("tokenChecker", String.valueOf(onlineCallModel.getFcm_Token()));
        Log.d("tokenChecker", "aksdbnkaskdn");

        Intent intent = new Intent(getApplicationContext(), OutgoingInvitationActivity.class);
        intent.putExtra("user", onlineCallModel);
        startActivity(intent);

    }
}