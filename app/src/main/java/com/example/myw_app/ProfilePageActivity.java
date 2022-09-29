package com.example.myw_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProfilePageActivity extends AppCompatActivity {

    private RecyclerView journalRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        journalRecyclerView = findViewById(R.id.journalRecyclerView);

        ArrayList<Journal> journals = new ArrayList<>();

        journals.add(new Journal("This is the first journal", "This is a journal entry"));
        journals.add(new Journal("This is the second journal", "1234213"));
        journals.add(new Journal("This is the third journal", "asdua"));
        journals.add(new Journal("This is the fourth journal", "asdadasd"));
        journals.add(new Journal("This is the fifth journal", "asdasod"));

        JournalRecyclerViewAdapter adapter = new JournalRecyclerViewAdapter(this);
        adapter.setJournals(journals);

        journalRecyclerView.setAdapter(adapter);
        journalRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}