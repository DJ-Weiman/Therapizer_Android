package com.example.therapyizer.activities.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.therapyizer.R;
import com.example.therapyizer.adapters.KHBooksRecyclerViewAdapter;
import com.example.therapyizer.databinding.ActivityKnowledgeHubBinding;
import com.example.therapyizer.models.KnowledgeHubBooksModel;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class KnowledgeHubActivity extends AppCompatActivity {

    ActivityKnowledgeHubBinding binding;
    ArrayList<KnowledgeHubBooksModel> knowledgeHubBooksModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_knowledge_hub);

        binding.booksTabButton.setBackgroundColor(getColor(R.color.white));
        binding.articlesTabButton.setBackgroundColor(getColor(R.color.appThemePurple));
        binding.journalsTabButton.setBackgroundColor(getColor(R.color.appThemePurple));
        binding.topActionBar.backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        setUpButtons();
        setUpKnowledgeHubBooksModels();
        setUpRecyclerView();
    }

    private void setUpButtons(){

        binding.booksTabButton.setOnClickListener(view -> {
            setUpKnowledgeHubBooksModels();
            setUpRecyclerView();

            binding.booksTabButton.setBackgroundColor(getColor(R.color.white));
            binding.articlesTabButton.setBackgroundColor(getColor(R.color.appThemePurple));
            binding.journalsTabButton.setBackgroundColor(getColor(R.color.appThemePurple));

        });

        binding.articlesTabButton.setOnClickListener(view -> {
            setUpKnowledgeHubArticlesModels();
            setUpRecyclerView();
            binding.booksTabButton.setBackgroundColor(getColor(R.color.appThemePurple));
            binding.articlesTabButton.setBackgroundColor(getColor(R.color.white));
            binding.journalsTabButton.setBackgroundColor(getColor(R.color.appThemePurple));
        });

        binding.journalsTabButton.setOnClickListener(view -> {
            setUpKnowledgeHubHJournalsModels();
            setUpRecyclerView();
            binding.booksTabButton.setBackgroundColor(getColor(R.color.appThemePurple));
            binding.articlesTabButton.setBackgroundColor(getColor(R.color.appThemePurple));
            binding.journalsTabButton.setBackgroundColor(getColor(R.color.white));
        });

    }

    private void setUpRecyclerView(){
        KHBooksRecyclerViewAdapter adapter = new KHBooksRecyclerViewAdapter(KnowledgeHubActivity.this, knowledgeHubBooksModels);
        binding.knowledgeHubBooksRecyclerView.setAdapter(adapter);
        binding.knowledgeHubBooksRecyclerView.setLayoutManager(new LinearLayoutManager(KnowledgeHubActivity.this));
    }
    private void setUpKnowledgeHubBooksModels(){

        this.knowledgeHubBooksModels.clear();

        String[] bookTitles = getResources().getStringArray(R.array.patient_knowledge_hub_books_titles);
        String[] bookAuthors = getResources().getStringArray(R.array.patient_knowledge_hub_books_authors);
        String[] bookDescriptions = getResources().getStringArray(R.array.patient_knowledge_hub_books_description);

        for (int i = 0; i<bookTitles.length; i++){
            knowledgeHubBooksModels.add(new KnowledgeHubBooksModel(
                    bookTitles[i],
                    bookAuthors[i],
                    bookDescriptions[i])
            );
        }
    }

    private void setUpKnowledgeHubArticlesModels(){

        this.knowledgeHubBooksModels.clear();

        String[] bookTitles = getResources().getStringArray(R.array.patient_knowledge_hub_articles_titles);
        String[] bookAuthors = getResources().getStringArray(R.array.patient_knowledge_hub_books_authors);
        String[] bookDescriptions = getResources().getStringArray(R.array.patient_knowledge_hub_books_description);

        for (int i = 0; i<bookTitles.length; i++){
            knowledgeHubBooksModels.add(new KnowledgeHubBooksModel(
                    bookTitles[i],
                    bookAuthors[i],
                    bookDescriptions[i])
            );
        }
    }

    private void setUpKnowledgeHubHJournalsModels(){

        this.knowledgeHubBooksModels.clear();

        String[] bookTitles = getResources().getStringArray(R.array.patient_knowledge_hub_journals_titles);
        String[] bookAuthors = getResources().getStringArray(R.array.patient_knowledge_hub_books_authors);
        String[] bookDescriptions = getResources().getStringArray(R.array.patient_knowledge_hub_books_description);

        for (int i = 0; i<bookTitles.length; i++){
            knowledgeHubBooksModels.add(new KnowledgeHubBooksModel(
                    bookTitles[i],
                    bookAuthors[i],
                    bookDescriptions[i])
            );
        }
    }
}