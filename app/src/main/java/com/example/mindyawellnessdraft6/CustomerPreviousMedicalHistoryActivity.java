package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class CustomerPreviousMedicalHistoryActivity extends AppCompatActivity {

    private MaterialToolbar customerPreviousMedicalHistoryToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_previous_medical_history);

        customerPreviousMedicalHistoryToolbar = findViewById(R.id.customerPreviousMedicalHistoryToolbar);

        customerPreviousMedicalHistoryToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerPreviousMedicalHistoryToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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