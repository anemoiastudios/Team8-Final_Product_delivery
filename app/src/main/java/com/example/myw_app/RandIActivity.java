package com.example.myw_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class RandIActivity extends AppCompatActivity {

    private RecyclerView rAndIRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rand_iactivity);

        rAndIRecyclerView = findViewById(R.id.rAndIRecyclerView);
    }
}