package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Provider;
import java.util.HashMap;

public class ProviderSignUpActivity extends AppCompatActivity {

    private TextInputEditText emailProviderSignUpEditText;
    private TextInputEditText fullNameProviderSignUpEditText;
    private TextInputEditText passwordProviderSignUpEditText;
    private TextInputEditText reInputPasswordProviderSignUpEditText;

    private TextInputLayout credentialsProviderSignUpTextInputLayout;
    private AutoCompleteTextView credentialsProviderSignUpAutoCompleteTextView;

    private MaterialButton finishButton;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_sign_up);

        emailProviderSignUpEditText = findViewById(R.id.emailProviderSignUpEditText);
        fullNameProviderSignUpEditText = findViewById(R.id.fullNameProviderSignUpEditText);
        passwordProviderSignUpEditText = findViewById(R.id.passwordProviderSignUpEditText);
        reInputPasswordProviderSignUpEditText = findViewById(R.id.reInputPasswordProviderSignUpEditText);

        credentialsProviderSignUpTextInputLayout = findViewById(R.id.credentialsProviderSignUpTextInputLayout);
        credentialsProviderSignUpAutoCompleteTextView = findViewById(R.id.credentialsProviderSignUpAutoCompleteTextView);

        String[] credentials = {"PhD \\ PsyD", "MFT", "LCSW", "LPC"};
        ArrayAdapter<String> credentialsAdapter = new ArrayAdapter<>(ProviderSignUpActivity.this, R.layout.list_credentials, credentials);
        credentialsProviderSignUpAutoCompleteTextView.setAdapter(credentialsAdapter);

        finishButton = findViewById(R.id.finishButton);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailProviderSignUpEditText.getText().toString();
                String fullName = fullNameProviderSignUpEditText.getText().toString();
                String password = passwordProviderSignUpEditText.getText().toString();
                String rePassword = reInputPasswordProviderSignUpEditText.getText().toString();
                String credential = credentialsProviderSignUpAutoCompleteTextView.getText().toString();

                if(email.length() == 0 || fullName.length() == 0 || password.length() == 0 || credential.length() == 0){
                    Toast.makeText(ProviderSignUpActivity.this, "Make sure you input something", Toast.LENGTH_SHORT).show();

                } else if(!password.equals(rePassword)){
                    Toast.makeText(ProviderSignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();

                } else {
                    auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            HashMap<String, Object> h = new HashMap<>();

                            h.put("email", email);
                            h.put("userType", "PROVIDER");
                            h.put("uid", auth.getCurrentUser().getUid());

                            // Add to the database of the current user.


                            // -----

                            databaseReference.child("Users").child(auth.getCurrentUser().getUid()).setValue(h).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ProviderSignUpActivity.this, "Success provider sign up", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(ProviderSignUpActivity.this, ProviderMainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish(); // Remove this and see what happens

                                    }
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ProviderSignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
}