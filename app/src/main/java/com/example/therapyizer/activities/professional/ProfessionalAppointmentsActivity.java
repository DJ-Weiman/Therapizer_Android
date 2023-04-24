package com.example.therapyizer.activities.professional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.therapyizer.R;
import com.example.therapyizer.adapters.ClientNavAdapter;
import com.example.therapyizer.adapters.NotesNavAdapter;
import com.example.therapyizer.databinding.ActivityProfessionalAppointmentsBinding;
import com.example.therapyizer.models.ClientModel;
import com.example.therapyizer.models.NotesModel;

import java.util.ArrayList;

public class ProfessionalAppointmentsActivity extends AppCompatActivity {

    ArrayList<ClientModel> appointmentsModels = new ArrayList<>();
    ActivityProfessionalAppointmentsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_professional_appointments);
        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());

        setAppointmentsModels();

        ClientNavAdapter adapter = new ClientNavAdapter(this, appointmentsModels);
        binding.notesRecyclerView.setAdapter(adapter);
        binding.notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setAppointmentsModels(){
        String[] appointmentsDates = getResources().getStringArray(R.array.notes_dates);
        String[] appointmentsClients = getResources().getStringArray(R.array.client_names);

        for(int i=0; i< appointmentsDates.length; i++){
            appointmentsModels.add(new ClientModel(appointmentsClients[i], "Next appointment : " + appointmentsDates[i]));
        }
    }
}