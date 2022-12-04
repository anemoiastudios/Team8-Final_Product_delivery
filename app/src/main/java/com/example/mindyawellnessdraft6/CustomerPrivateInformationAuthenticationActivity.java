package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerPrivateInformationAuthenticationActivity extends AppCompatActivity {

    private MaterialToolbar customerPrivateInformationAuthenticationToolbar;
    private TextInputEditText customerPrivateInformationAuthEditText;
    private MaterialButton customerPrivateInformationAuthMaterialButton;

    private FirebaseAuth auth;
    // private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private String correctPassword;
    private String inputPassword;

    private ValueEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_private_information_authentication);

        customerPrivateInformationAuthenticationToolbar = findViewById(R.id.customerPrivateInformationAuthenticationToolbar);
        customerPrivateInformationAuthEditText = findViewById(R.id.customerPrivateInformationAuthEditText);
        customerPrivateInformationAuthMaterialButton = findViewById(R.id.customerPrivateInformationAuthMaterialButton);

        auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("credentials");

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
                inputPassword = customerPrivateInformationAuthEditText.getText().toString();

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        correctPassword = snapshot.child("password").getValue().toString();

                        Log.d("inputPassword", inputPassword);
                        Log.d("inputPassword", correctPassword);

                        if(inputPassword.equals(correctPassword)){
                            Intent intent = new Intent(CustomerPrivateInformationAuthenticationActivity.this, CustomerPrivateInformationActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(CustomerPrivateInformationAuthenticationActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });


            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}