package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class CustomerUpdateProfileActivity extends AppCompatActivity {

    private MaterialToolbar customerUpdateProfileToolbar;
    private MaterialButton customerEditProfileSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_update_profile);

        customerUpdateProfileToolbar = findViewById(R.id.customerUpdateProfileToolbar);
        customerEditProfileSaveButton = findViewById(R.id.customerEditProfileSaveButton);

        customerUpdateProfileToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerEditProfileSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}