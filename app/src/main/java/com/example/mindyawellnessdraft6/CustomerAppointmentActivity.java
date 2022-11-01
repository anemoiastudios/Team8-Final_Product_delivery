package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class CustomerAppointmentActivity extends AppCompatActivity {

    private MaterialToolbar customerAppointmentEntryToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_appointment);

        customerAppointmentEntryToolbar = findViewById(R.id.customerAppointmentEntryToolbar);

        customerAppointmentEntryToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}