package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class CustomerResetPasswordActivity extends AppCompatActivity {

    private MaterialToolbar customerResetPasswordToolbar;

    private MaterialButton customerResetPasswordMaterialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reset_password);

        customerResetPasswordToolbar = findViewById(R.id.customerResetPasswordToolbar);
        customerResetPasswordMaterialButton = findViewById(R.id.customerResetPasswordMaterialButton);

        customerResetPasswordToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerResetPasswordMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}