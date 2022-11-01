package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class CustomerUpdateNotificationsActivity extends AppCompatActivity {

    private MaterialToolbar customerUpdateNotificationsToolbar;

    private MaterialButton customerUpdateNotificationsSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_update_notifications);

        customerUpdateNotificationsToolbar = findViewById(R.id.customerUpdateNotificationsToolbar);
        customerUpdateNotificationsSaveButton = findViewById(R.id.customerUpdateNotificationsSaveButton);

        customerUpdateNotificationsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerUpdateNotificationsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}