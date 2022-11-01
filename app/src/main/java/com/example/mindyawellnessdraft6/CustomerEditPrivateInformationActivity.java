package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class CustomerEditPrivateInformationActivity extends AppCompatActivity {

    private MaterialToolbar customerEditPrivateInformationToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_private_information);

        customerEditPrivateInformationToolbar = findViewById(R.id.customerEditPrivateInformationToolbar);

        customerEditPrivateInformationToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}