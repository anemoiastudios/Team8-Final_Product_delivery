package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class ProviderSettingsActivity extends AppCompatActivity {

    private MaterialToolbar providerSettingsToolbar;
    private ListView providerSettingsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_settings);

        providerSettingsToolbar = findViewById(R.id.providerSettingsToolbar);
        providerSettingsListView = findViewById(R.id.providerSettingsListView);

        providerSettingsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
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

        ArrayAdapter settingsEntriesAdapter = new ArrayAdapter(ProviderSettingsActivity.this, android.R.layout.simple_list_item_1, settingEntries);
        providerSettingsListView.setAdapter(settingsEntriesAdapter);

        providerSettingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String entry = String.valueOf(adapterView.getItemAtPosition(i));
                Intent intent;

                switch(entry){
                    case "Update Profile":
                        intent = new Intent(ProviderSettingsActivity.this, ProviderUpdateProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case "Update Notifications":
                        intent = new Intent(ProviderSettingsActivity.this, ProviderUpdateNotificationsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case "Reset Password":
                        intent = new Intent(ProviderSettingsActivity.this, CustomerResetPasswordActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case "Private Information":
                        intent = new Intent(ProviderSettingsActivity.this, ProviderPrivateInformationActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;

                }
            }
        });

    }
}