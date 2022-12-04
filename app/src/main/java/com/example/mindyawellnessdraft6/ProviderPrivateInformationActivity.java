package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProviderPrivateInformationActivity extends AppCompatActivity {

    private MaterialToolbar providerIdentificationToolbar;
    private TextInputEditText providerEditNameEditText;
    private AutoCompleteTextView credentialsProviderSignUpAutoCompleteTextView;

    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_private_information);

        providerIdentificationToolbar = findViewById(R.id.providerIdentificationToolbar);

        providerEditNameEditText = findViewById(R.id.providerEditNameEditText);
        credentialsProviderSignUpAutoCompleteTextView = findViewById(R.id.credentialsProviderSignUpAutoCompleteTextView);

        String[] credentials = {"PhD \\ PsyD", "MFT", "LCSW", "LPC"};
        ArrayAdapter<String> credentialsAdapter = new ArrayAdapter<>(ProviderPrivateInformationActivity.this, R.layout.list_credentials, credentials);
        credentialsProviderSignUpAutoCompleteTextView.setAdapter(credentialsAdapter);

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUid);

        providerIdentificationToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        providerIdentificationToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.customerEditPrivateInformationEntriesButton:
                        databaseReference.child("privateInfo").child("fullName").setValue(providerEditNameEditText.getText().toString());
                        databaseReference.child("privateInfo").child("credential").setValue(credentialsProviderSignUpAutoCompleteTextView.getText().toString());
                        return true;

                }

                return false;
            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                providerEditNameEditText.setText(snapshot.child("privateInfo").child("fullName").getValue().toString());



                if(snapshot.child("privateInfo").child("credential").getValue().toString().equals("PhD \\ PsyD")){
                    credentialsProviderSignUpAutoCompleteTextView.setText(credentialsAdapter.getItem(0), false);

                } else if (snapshot.child("privateInfo").child("credential").getValue().toString().equals("MFT")){
                    credentialsProviderSignUpAutoCompleteTextView.setText(credentialsAdapter.getItem(1), false);

                } else if (snapshot.child("privateInfo").child("credential").getValue().toString().equals("LCSW")) {
                    credentialsProviderSignUpAutoCompleteTextView.setText(credentialsAdapter.getItem(2), false);

                } else {
                    credentialsProviderSignUpAutoCompleteTextView.setText(credentialsAdapter.getItem(3), false);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}