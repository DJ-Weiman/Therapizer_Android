package com.example.therapyizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.therapyizer.R;
import com.example.therapyizer.interfaces.PatientNavInterface;
import com.example.therapyizer.models.PatientNavCardModel;

import java.util.ArrayList;

public class PatientNavAdapter extends RecyclerView.Adapter<PatientNavAdapter.MyViewHolder> {

    private final PatientNavInterface patientNavInterface;

    Context context;
    ArrayList<PatientNavCardModel> patientNavCardModels;

    public PatientNavAdapter(Context context, ArrayList<PatientNavCardModel> patientNavCardModels, PatientNavInterface patientNavInterface) {
        this.context = context;
        this.patientNavCardModels = patientNavCardModels;
        this.patientNavInterface = patientNavInterface;
    }

    @NonNull
    @Override
    public PatientNavAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.patient_recycler_view_row, parent, false);
        return new PatientNavAdapter.MyViewHolder(view, patientNavInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientNavAdapter.MyViewHolder holder, int position) {
        holder.navTitle.setText(patientNavCardModels.get(position).getNavTitle());
        holder.navText.setText(patientNavCardModels.get(position).getNavText());
        holder.imageView.setImageResource(patientNavCardModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return patientNavCardModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView navTitle, navText;
        CardView continueButton;

        public MyViewHolder(@NonNull View itemView, PatientNavInterface patientNavInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.patientNavImage);
            navTitle = itemView.findViewById(R.id.patientNavTitle);
            navText = itemView.findViewById(R.id.patientNavText);

            continueButton = itemView.findViewById(R.id.patientContinueButton);
            continueButton.setOnClickListener(view -> {

                if(patientNavInterface != null){
                    int pos = getAbsoluteAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        patientNavInterface.onItemClick(pos);
                    }
                }

            });
        }
    }
}
