package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class RecoveryAccountActivity extends AppCompatActivity {

    private MaterialButton recoverAccountButton;
    private EditText emailRecoverAccountEditText;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_account);

        recoverAccountButton = findViewById(R.id.recoverAccountButton);
        emailRecoverAccountEditText = findViewById(R.id.emailRecoverAccountEditText);

        auth = FirebaseAuth.getInstance();

        recoverAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailRecoverAccountEditText.getText().toString();

                if(email.length() == 0){
                    Toast.makeText(RecoveryAccountActivity.this, "Enter an email", Toast.LENGTH_SHORT).show();

                } else {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(RecoveryAccountActivity.this, "Check email", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
            }
        });

    }
}