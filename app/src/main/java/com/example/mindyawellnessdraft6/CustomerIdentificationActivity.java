package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class CustomerIdentificationActivity extends AppCompatActivity {

    private MaterialToolbar customerIdentificationToolbar;

    private AutoCompleteTextView customerEditGenderAutoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_identification);

        customerIdentificationToolbar = findViewById(R.id.customerIdentificationToolbar);

        customerEditGenderAutoCompleteTextView = findViewById(R.id.customerEditGenderAutoCompleteTextView);


        String[] genders = {"Male", "Female", "None"};
        ArrayAdapter<String> gendersAdapter = new ArrayAdapter<>(CustomerIdentificationActivity.this, R.layout.list_gender, genders);
        customerEditGenderAutoCompleteTextView.setAdapter(gendersAdapter);

        customerIdentificationToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerIdentificationToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.customerEditPrivateInformationEntriesButton:
                        finish();
                        return true;

                }

                return false;
            }
        });

    }

}