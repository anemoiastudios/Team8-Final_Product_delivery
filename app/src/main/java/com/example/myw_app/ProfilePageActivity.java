package com.example.myw_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ProfilePageActivity extends AppCompatActivity {

    private RecyclerView journalRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        journalRecyclerView = findViewById(R.id.journalRecyclerView);


    }
}