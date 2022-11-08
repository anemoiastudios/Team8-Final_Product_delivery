package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class CustomerAllergiesActivity extends AppCompatActivity {

    private MaterialToolbar customerAllergiesToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_allergies);

        customerAllergiesToolbar = findViewById(R.id.customerAllergiesToolbar);

        customerAllergiesToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerAllergiesToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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