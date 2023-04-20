package com.example.therapyizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.therapyizer.R;
import com.example.therapyizer.models.KnowledgeHubBooksModel;

import java.util.ArrayList;

public class KHBooksRecyclerViewAdapter extends RecyclerView.Adapter<KHBooksRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<KnowledgeHubBooksModel> knowledgeHubBooksModels;


    public KHBooksRecyclerViewAdapter(Context context, ArrayList<KnowledgeHubBooksModel> knowledgeHubBooksModels){
        this.context = context;
        this.knowledgeHubBooksModels = knowledgeHubBooksModels;
    }

    @NonNull
    @Override
    public KHBooksRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.knowledge_hub_books_recycler_view_row, parent, false);
        return new KHBooksRecyclerViewAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull KHBooksRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.booksTitle.setText(knowledgeHubBooksModels.get(position).getBookTitle());
        holder.booksAuthor.setText(knowledgeHubBooksModels.get(position).getAuthorName());
        holder.booksDescription.setText(knowledgeHubBooksModels.get(position).getBookDescription());

    }

    @Override
    public int getItemCount() {
        return knowledgeHubBooksModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView booksTitle, booksDescription, booksAuthor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            booksTitle = itemView.findViewById(R.id.bookTitleText);
            booksAuthor = itemView.findViewById(R.id.bookAuthorText);
            booksDescription = itemView.findViewById(R.id.bookDescriptionText);
        }
    }
}
