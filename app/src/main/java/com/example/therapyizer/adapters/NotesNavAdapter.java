package com.example.therapyizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.therapyizer.R;
import com.example.therapyizer.models.NotesModel;

import java.util.ArrayList;

public class NotesNavAdapter extends RecyclerView.Adapter<NotesNavAdapter.MyViewHolder> {

    Context context;
    ArrayList<NotesModel> notesModels;

    public NotesNavAdapter(Context context, ArrayList<NotesModel> notesModels) {
        this.context = context;
        this.notesModels = notesModels;
    }

    @NonNull
    @Override
    public NotesNavAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notes_recycler_view_row, parent, false);
        return new NotesNavAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesNavAdapter.MyViewHolder holder, int position) {
        holder.notesDate.setText(notesModels.get(position).getNotesDate());
        holder.notesClient.setText(notesModels.get(position).getNotesClient());
    }

    @Override
    public int getItemCount() {
        return notesModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView notesDate, notesClient;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            notesDate = itemView.findViewById(R.id.notesDate);
            notesClient = itemView.findViewById(R.id.notesClientName);
        }
    }
}
