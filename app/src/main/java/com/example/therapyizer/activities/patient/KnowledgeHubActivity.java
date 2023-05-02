package com.example.therapyizer.activities.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.therapyizer.R;
import com.example.therapyizer.adapters.KHBooksRecyclerViewAdapter;
import com.example.therapyizer.databinding.ActivityKnowledgeHubBinding;
import com.example.therapyizer.interfaces.PatientNavInterface;
import com.example.therapyizer.models.KnowledgeHubBooksModel;
import com.example.therapyizer.utilities.KHTab;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class KnowledgeHubActivity extends AppCompatActivity implements PatientNavInterface {

    ActivityKnowledgeHubBinding binding;
    ArrayList<KnowledgeHubBooksModel> knowledgeHubBooksModels = new ArrayList<>();
    public static KHTab khTab;

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

        khTab = KHTab.BOOKS;

        setUpButtons();
        setUpKnowledgeHubBooksModels();
        setUpRecyclerView();
    }

    private void setUpButtons(){

        binding.booksTabButton.setOnClickListener(view -> {
            setUpKnowledgeHubBooksModels();
            setUpRecyclerView();
            khTab = KHTab.BOOKS;

            binding.booksTabButton.setBackgroundColor(getColor(R.color.white));
            binding.articlesTabButton.setBackgroundColor(getColor(R.color.appThemePurple));
            binding.journalsTabButton.setBackgroundColor(getColor(R.color.appThemePurple));

        });

        binding.articlesTabButton.setOnClickListener(view -> {
            setUpKnowledgeHubArticlesModels();
            setUpRecyclerView();
            khTab = KHTab.ARTICLES;
            binding.booksTabButton.setBackgroundColor(getColor(R.color.appThemePurple));
            binding.articlesTabButton.setBackgroundColor(getColor(R.color.white));
            binding.journalsTabButton.setBackgroundColor(getColor(R.color.appThemePurple));
        });

        binding.journalsTabButton.setOnClickListener(view -> {
            setUpKnowledgeHubHJournalsModels();
            setUpRecyclerView();
            khTab = KHTab.JOURNALS;
            binding.booksTabButton.setBackgroundColor(getColor(R.color.appThemePurple));
            binding.articlesTabButton.setBackgroundColor(getColor(R.color.appThemePurple));
            binding.journalsTabButton.setBackgroundColor(getColor(R.color.white));
        });

    }

    private void setUpRecyclerView(){
        KHBooksRecyclerViewAdapter adapter = new KHBooksRecyclerViewAdapter(KnowledgeHubActivity.this, knowledgeHubBooksModels, this);
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
        String[] bookAuthors = getResources().getStringArray(R.array.patient_knowledge_hub_articles_authors);
        String[] bookDescriptions = getResources().getStringArray(R.array.patient_knowledge_hub_articles_description);

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
        String[] bookAuthors = getResources().getStringArray(R.array.patient_knowledge_hub_journals_authors);
        String[] bookDescriptions = getResources().getStringArray(R.array.patient_knowledge_hub_journals_description);

        for (int i = 0; i<bookTitles.length; i++){
            knowledgeHubBooksModels.add(new KnowledgeHubBooksModel(
                    bookTitles[i],
                    bookAuthors[i],
                    bookDescriptions[i])
            );
        }
    }

    @Override
    public void onItemClick(int position) {

        if(this.khTab == KHTab.BOOKS){
            Intent book1Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sciencedirect.com/book/9780128169797/cognitive-clinical-and-neural-aspects-of-drug-addiction"));
            Intent book2Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sciencedirect.com/book/9780123869371/drugs-addiction-and-the-brain"));

            switch(position){
                case 0:
                    startActivity(book1Intent);
                    break;
                case 1:
                    startActivity(book2Intent);
                    break;
            }
        }else if(this.khTab == KHTab.ARTICLES){
            Intent article1Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.helpguide.org/articles/addictions/drug-abuse-and-addiction.htm"));
            Intent article2Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nida.nih.gov/publications/drugfacts/understanding-drug-use-addiction"));
            Intent article3Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://thepathwayprogram.com/drug-addiction/"));

            switch(position){
                case 0:
                    startActivity(article1Intent);
                    break;
                case 1:
                    startActivity(article2Intent);
                    break;
                case 2:
                    startActivity(article3Intent);
                    break;
            }
        } else if (this.khTab == KHTab.JOURNALS) {
            Intent journal1Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.primescholars.com/drug-abuse.html"));
            Intent journal2Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://journals.lww.com/journaladdictionmedicine/pages/default.aspx"));
            Intent journal3Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://journals.sagepub.com/home/jod"));

            switch(position){
                case 0:
                    startActivity(journal1Intent);
                    break;
                case 1:
                    startActivity(journal2Intent);
                    break;
                case 2:
                    startActivity(journal3Intent);
                    break;
            }
        }


    }
}