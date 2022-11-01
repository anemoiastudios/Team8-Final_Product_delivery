package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.appbar.MaterialToolbar;

public class ProviderCustomerProfileActivity extends AppCompatActivity {

    private MaterialToolbar providerCustomerProfileToolbar;

    private ImageButton customerProviderProfileMessageImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_customer_profile);

        providerCustomerProfileToolbar = findViewById(R.id.providerCustomerProfileToolbar);

        providerCustomerProfileToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerProviderProfileMessageImageButton = findViewById(R.id.customerProviderProfileMessageImageButton);

        customerProviderProfileMessageImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProviderCustomerProfileActivity.this, ProviderChatWithCustomerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });








    }
}