package com.example.therapyizer.activities.professional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.therapyizer.R;
import com.example.therapyizer.adapters.NotesNavAdapter;
import com.example.therapyizer.databinding.ActivityProfessionalNotesBinding;
import com.example.therapyizer.models.NotesModel;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class ProfessionalNotesActivity extends AppCompatActivity {

    ArrayList<NotesModel> notesModels = new ArrayList<>();
    ActivityProfessionalNotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_professional_notes);
        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());

        setNotesModels();

        NotesNavAdapter adapter = new NotesNavAdapter(this, notesModels);
        binding.notesRecyclerView.setAdapter(adapter);
        binding.notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setNotesModels(){
        String[] notesDate = getResources().getStringArray(R.array.notes_dates);
        String[] notesClients = getResources().getStringArray(R.array.client_names);

        for(int i=0; i< notesDate.length; i++){
            notesModels.add(new NotesModel(notesDate[i], notesClients[i]));
        }
    }
}