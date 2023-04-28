package com.example.therapyizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.therapyizer.R;
import com.example.therapyizer.interfaces.OnlineCallInterface;
import com.example.therapyizer.models.OnlineCallModel;


import java.util.ArrayList;

public class OnlineCallNavAdapter extends RecyclerView.Adapter<OnlineCallNavAdapter.MyViewHolder> {

    private final OnlineCallInterface onlineCallInterface;

    Context context;
    ArrayList<OnlineCallModel> onlineCallModels;

    public OnlineCallNavAdapter(OnlineCallInterface onlineCallInterface, Context context, ArrayList<OnlineCallModel> onlineCallModels) {
        this.onlineCallInterface = onlineCallInterface;
        this.context = context;
        this.onlineCallModels = onlineCallModels;
    }

    @NonNull
    @Override
    public OnlineCallNavAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.therapy_user_recycler_view_row, parent, false);
        return new OnlineCallNavAdapter.MyViewHolder(view, onlineCallInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineCallNavAdapter.MyViewHolder holder, int position) {
        holder.doctorName.setText(onlineCallModels.get(position).getDoctorName());
        holder.doctorProfession.setText(onlineCallModels.get(position).getDoctorProfession());
    }

    @Override
    public int getItemCount() {
        return onlineCallModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView doctorName, doctorProfession;
        CardView callButton;

        public MyViewHolder(@NonNull View itemView, OnlineCallInterface onlineCallInterface) {
            super(itemView);

            doctorName = itemView.findViewById(R.id.doctorName);
            doctorProfession = itemView.findViewById(R.id.doctorProfession);

            callButton = itemView.findViewById(R.id.callButton);
            callButton.setOnClickListener(view -> {
                if (onlineCallInterface != null) {
                    int pos = getAbsoluteAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        onlineCallInterface.onCallClicked(onlineCallModels.get(pos));
                    }
                }
            });
        }
    }
}
