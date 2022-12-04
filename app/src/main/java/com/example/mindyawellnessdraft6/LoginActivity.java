package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailLoginEditText;
    private TextInputEditText passwordLoginEditText;
    public TextView recoverAccountLoginTextView;
    private MaterialButton loginLoginButton;
    private TextView registerLoginTextView;
    // private Button registerLoginButton;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLoginEditText = findViewById(R.id.emailLoginEditText);
        passwordLoginEditText = findViewById(R.id.passwordLoginEditText);
        recoverAccountLoginTextView = findViewById(R.id.recoverAccountLoginTextView);
        loginLoginButton = findViewById(R.id.loginLoginButton);
        registerLoginTextView = findViewById(R.id.registerLoginTextView);
        // registerLoginButton = findViewById(R.id.registerLoginButton);

        auth = FirebaseAuth.getInstance();

        recoverAccountLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RecoveryAccountActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        registerLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        loginLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailLoginEditText.getText().toString();
                String password = passwordLoginEditText.getText().toString();

                if(email.length() == 0 || password.length() == 0){
                    Toast.makeText(LoginActivity.this, "Input something", Toast.LENGTH_SHORT).show();

                } else {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUID);

                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String userType = snapshot.child("credentials").child("userType").getValue().toString();

                                        if(userType.equals("CUSTOMER")){
                                            Intent intent = new Intent(LoginActivity.this, CustomerMainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                            finish(); // Remove this and see what happens

                                        } else if (userType.equals("PROVIDER")) {
                                            Intent intent = new Intent(LoginActivity.this, ProviderMainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                            finish(); // Remove this and see what happens

                                        } else {
                                            Toast.makeText(LoginActivity.this, "Make sure you have account", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });





    }

    @Override
    public void onBackPressed() {
        return;
    }
}