package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class CustomerChatWithProviderActivity extends AppCompatActivity {

    private MaterialToolbar customerChatWithProviderMaterialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_chat_with_provider);

        customerChatWithProviderMaterialToolbar = findViewById(R.id.customerChatWithProviderMaterialToolbar);

        customerChatWithProviderMaterialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}