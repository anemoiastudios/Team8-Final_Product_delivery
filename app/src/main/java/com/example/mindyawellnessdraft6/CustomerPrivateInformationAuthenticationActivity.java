package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class CustomerPrivateInformationAuthenticationActivity extends AppCompatActivity {

    private MaterialToolbar customerPrivateInformationAuthenticationToolbar;
    private TextInputEditText customerPrivateInformationAuthEditText;
    private MaterialButton customerPrivateInformationAuthMaterialButton;

    // private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_private_information_authentication);

        customerPrivateInformationAuthenticationToolbar = findViewById(R.id.customerPrivateInformationAuthenticationToolbar);
        customerPrivateInformationAuthEditText = findViewById(R.id.customerPrivateInformationAuthEditText);
        customerPrivateInformationAuthMaterialButton = findViewById(R.id.customerPrivateInformationAuthMaterialButton);

        customerPrivateInformationAuthenticationToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerPrivateInformationAuthMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check to make sure the password is correct by checking the auth. of user.
                // If not correct display a Toast

                // Else if everything check out...
                Intent intent = new Intent(CustomerPrivateInformationAuthenticationActivity.this, CustomerPrivateInformationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}