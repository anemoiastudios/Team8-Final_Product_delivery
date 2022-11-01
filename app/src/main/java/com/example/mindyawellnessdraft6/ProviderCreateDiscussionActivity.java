package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class ProviderCreateDiscussionActivity extends AppCompatActivity {

    private MaterialToolbar providerCreateDiscussionToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_create_discussion);

        providerCreateDiscussionToolbar = findViewById(R.id.providerCreateDiscussionToolbar);

        providerCreateDiscussionToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}