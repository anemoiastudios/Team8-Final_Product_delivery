package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class CustomerMoreEntriesActivity extends AppCompatActivity {

    private MaterialToolbar customerMoreEntryMaterialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_more_entries);

        customerMoreEntryMaterialToolbar = findViewById(R.id.customerMoreEntryMaterialToolbar);

        customerMoreEntryMaterialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}