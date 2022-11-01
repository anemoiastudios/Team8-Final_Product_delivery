package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class ProviderChatWithCustomerActivity extends AppCompatActivity {

    private MaterialToolbar providerChatWithCustomerToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_chat_with_customer);

        providerChatWithCustomerToolbar = findViewById(R.id.providerChatWithCustomerToolbar);

        providerChatWithCustomerToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}