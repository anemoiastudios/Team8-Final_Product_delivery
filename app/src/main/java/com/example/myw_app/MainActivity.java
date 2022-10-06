package com.example.myw_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // private RecyclerView rAndIRecyclerView;
    // private RecyclerView journalRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.edit_profile);
        // setContentView(R.layout.activity_rand_iactivity);
        // setContentView(R.layout.activity_profile_page);

        /*
        rAndIRecyclerView = findViewById(R.id.rAndIRecyclerView);

        ArrayList<RAndIEntry> entries = new ArrayList<>();
        entries.add(new RAndIEntry("Housing", "Get more information about housing for the mentally incapacitated"));
        entries.add(new RAndIEntry("Grants", "Learn more about grants"));
        entries.add(new RAndIEntry("Bipolar Disorder", "Learn more about bipolar disorders"));
        entries.add(new RAndIEntry("Housing", "Learn more about housing"));

        RAndIRecyclerViewAdapter adapter = new RAndIRecyclerViewAdapter(this);
        adapter.setEntries(entries);

        rAndIRecyclerView.setAdapter(adapter);
        rAndIRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         */


        /*
        journalRecyclerView = findViewById(R.id.journalRecyclerView);

        ArrayList<Journal> journals = new ArrayList<>();

        journals.add(new Journal("This is the first journal", "This is a journal entry"));
        journals.add(new Journal("This is the second journal", "1234213"));
        journals.add(new Journal("This is the third journal", "asdua"));
        journals.add(new Journal("This is the fourth journal", "asdadasd"));
        journals.add(new Journal("This is the fifth journal", "asdadasd"));

        JournalRecyclerViewAdapter adapter = new JournalRecyclerViewAdapter(this);
        adapter.setJournals(journals);

        journalRecyclerView.setAdapter(adapter);
        journalRecyclerView.setLayoutManager(new LinearLayoutManager(this));

         */
    }
}