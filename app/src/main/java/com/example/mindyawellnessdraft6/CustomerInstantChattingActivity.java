package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class CustomerInstantChattingActivity extends AppCompatActivity {

    private MaterialToolbar customerInstantChattingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_instant_chatting);

        customerInstantChattingToolbar = findViewById(R.id.customerInstantChattingToolbar);

        customerInstantChattingToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}