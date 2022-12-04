package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class CustomerSettingsActivity extends AppCompatActivity {

    private MaterialToolbar customerSettingsToolbar;
    private ListView customerSettingsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_settings);

        customerSettingsToolbar = findViewById(R.id.customerSettingsToolbar);
        customerSettingsListView = findViewById(R.id.customerSettingsListView);

        customerSettingsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ArrayList<String> settingEntries = new ArrayList<>();
        settingEntries.add("Update Profile");
        settingEntries.add("Update Notifications");
        settingEntries.add("Reset Password");
        settingEntries.add("Private Information");

        ArrayAdapter settingsEntriesAdapter = new ArrayAdapter(CustomerSettingsActivity.this, android.R.layout.simple_list_item_1, settingEntries);
        customerSettingsListView.setAdapter(settingsEntriesAdapter);

        customerSettingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String entry = String.valueOf(adapterView.getItemAtPosition(i));
                Intent intent;

                switch(entry){
                    case "Update Profile":
                        intent = new Intent(CustomerSettingsActivity.this, CustomerUpdateProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case "Update Notifications":
                        intent = new Intent(CustomerSettingsActivity.this, CustomerUpdateNotificationsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case "Reset Password":
                        intent = new Intent(CustomerSettingsActivity.this, CustomerResetPasswordActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case "Private Information":
                        intent = new Intent(CustomerSettingsActivity.this, CustomerPrivateInformationAuthenticationActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                }
            }
        });



    }


}