package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class CustomerSetAppointmentActivity extends AppCompatActivity {

    private MaterialToolbar customerSetAppointmentToolbar;
    private MaterialButton customerSetAppointmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_set_appointment);

        customerSetAppointmentToolbar = findViewById(R.id.customerSetAppointmentToolbar);
        customerSetAppointmentButton = findViewById(R.id.customerSetAppointmentButton);

        customerSetAppointmentToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerSetAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CustomerSetAppointmentActivity.this, "You set up an appointment", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}