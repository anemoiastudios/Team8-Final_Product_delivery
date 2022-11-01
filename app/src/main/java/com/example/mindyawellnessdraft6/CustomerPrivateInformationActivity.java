package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class CustomerPrivateInformationActivity extends AppCompatActivity {

    private MaterialToolbar customerPrivateInformationToolbar;
    private ListView customerPrivateInformationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_private_information);

        customerPrivateInformationToolbar = findViewById(R.id.customerPrivateInformationToolbar);
        customerPrivateInformationListView = findViewById(R.id.customerPrivateInformationListView);

        customerPrivateInformationToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerPrivateInformationActivity.this, CustomerSettingsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        customerPrivateInformationToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;

                switch(item.getItemId()){
                    case R.id.customerEditPrivateInformationButton:
                        intent = new Intent(CustomerPrivateInformationActivity.this, CustomerEditPrivateInformationActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;
                }

                return false;
            }
        });

        ArrayList<String> privateInformationEntries = new ArrayList<>();
        privateInformationEntries.add("Identification and Measurement");
        privateInformationEntries.add("Allergies");
        privateInformationEntries.add("Medical Conditions");
        privateInformationEntries.add("Medications");
        privateInformationEntries.add("On-going Symptoms");
        privateInformationEntries.add("Previous Medical History");
        privateInformationEntries.add("Doctor's Notes");

        ArrayAdapter privateInformationEntriesAdapter = new ArrayAdapter(CustomerPrivateInformationActivity.this, android.R.layout.simple_list_item_1, privateInformationEntries);
        customerPrivateInformationListView.setAdapter(privateInformationEntriesAdapter);

        customerPrivateInformationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String entry = String.valueOf(adapterView.getItemAtPosition(i));
                Intent intent;

                switch(entry){
                    case "Identification and Measurement":
                        intent = new Intent(CustomerPrivateInformationActivity.this, CustomerIdentificationActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case "Allergies":
                        intent = new Intent(CustomerPrivateInformationActivity.this, CustomerAllergiesActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case "Medical Conditions":
                        intent = new Intent(CustomerPrivateInformationActivity.this, CustomerMedicalConditionsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case "Medications":
                        intent = new Intent(CustomerPrivateInformationActivity.this, CustomerMedicationsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case "On-going Symptoms":
                        intent = new Intent(CustomerPrivateInformationActivity.this, CustomerSymptomsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case "Previous Medical History":
                        intent = new Intent(CustomerPrivateInformationActivity.this, CustomerPreviousMedicalHistoryActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case "Doctor's Notes":
                        intent = new Intent(CustomerPrivateInformationActivity.this, CustomerDoctorsNoteActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        return;
    }
}