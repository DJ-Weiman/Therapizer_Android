package com.example.therapyizer.activities.professional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.therapyizer.R;
import com.example.therapyizer.adapters.ClientNavAdapter;
import com.example.therapyizer.databinding.ActivityProfessionalClientsBinding;
import com.example.therapyizer.models.ClientModel;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class ProfessionalClientsActivity extends AppCompatActivity {

    ArrayList<ClientModel> clientModels = new ArrayList<>();
    ActivityProfessionalClientsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_professional_clients);
        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());

        setClientModels();

        ClientNavAdapter adapter = new ClientNavAdapter(this, clientModels);
        binding.clientRecyclerView.setAdapter(adapter);
        binding.clientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setClientModels(){
        String[] clientNames = getResources().getStringArray(R.array.client_names);
        String[] clientAges = getResources().getStringArray(R.array.client_ages);

        for(int i=0; i<clientNames.length; i++){
            clientModels.add(new ClientModel(clientNames[i],clientAges[i]));
        }
    }
}