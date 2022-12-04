package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerPreviousMedicalHistoryActivity extends AppCompatActivity {

    private MaterialToolbar customerPreviousMedicalHistoryToolbar;

    private TextInputEditText customerEditMedicalHistoryEditText;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_previous_medical_history);

        customerPreviousMedicalHistoryToolbar = findViewById(R.id.customerPreviousMedicalHistoryToolbar);

        customerEditMedicalHistoryEditText = findViewById(R.id.customerEditMedicalHistoryEditText);

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUid);

        customerPreviousMedicalHistoryToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerPreviousMedicalHistoryToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.customerEditPrivateInformationEntriesButton:
                        databaseReference.child("privateInfo").child("medicalHistory").setValue(customerEditMedicalHistoryEditText.getText().toString());

                        finish();
                        return true;
                }

                return false;
            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                customerEditMedicalHistoryEditText.setText(snapshot.child("privateInfo").child("medicalHistory").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}