package com.example.therapyizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.therapyizer.R;
import com.example.therapyizer.models.ClientModel;

import java.util.ArrayList;

public class ClientNavAdapter extends RecyclerView.Adapter<ClientNavAdapter.MyViewHolder> {

    Context context;
    ArrayList<ClientModel> clientModels;

    public ClientNavAdapter(Context context, ArrayList<ClientModel> clientModels) {
        this.context = context;
        this.clientModels = clientModels;
    }

    @NonNull
    @Override
    public ClientNavAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.client_recycler_view_row, parent, false);
        return new ClientNavAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientNavAdapter.MyViewHolder holder, int position) {
        holder.clientName.setText(clientModels.get(position).getClientName());
        holder.clientAge.setText(clientModels.get(position).getClientAge());
    }

    @Override
    public int getItemCount() {
        return clientModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView clientName, clientAge;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            clientName = itemView.findViewById(R.id.clientName);
            clientAge = itemView.findViewById(R.id.clientAge);
        }
    }
}
