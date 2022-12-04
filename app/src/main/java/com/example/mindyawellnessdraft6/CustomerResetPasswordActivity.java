package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerResetPasswordActivity extends AppCompatActivity {

    private MaterialToolbar customerResetPasswordToolbar;

    private EditText customerResetPasswordNewPasswordEditText;

    private FirebaseAuth auth;

    private MaterialButton customerResetPasswordMaterialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reset_password);

        customerResetPasswordToolbar = findViewById(R.id.customerResetPasswordToolbar);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        customerResetPasswordNewPasswordEditText = findViewById(R.id.customerResetPasswordNewPasswordEditText);

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
                user.updatePassword(customerResetPasswordNewPasswordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        auth.signOut();

                        Intent intent = new Intent(CustomerResetPasswordActivity.this, WelcomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}