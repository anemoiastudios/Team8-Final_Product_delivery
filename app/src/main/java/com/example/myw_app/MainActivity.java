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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide(); //hides top bar
        System.out.println("Hello");
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.edit_profile);
        // setContentView(R.layout.activity_rand_iactivity);

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
    }
}