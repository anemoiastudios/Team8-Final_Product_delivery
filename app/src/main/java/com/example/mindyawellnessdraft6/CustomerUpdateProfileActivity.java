package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class CustomerUpdateProfileActivity extends AppCompatActivity {

    private MaterialToolbar customerUpdateProfileToolbar;
    private MaterialButton customerEditProfileSaveButton;

    public static EditText customerEditMoodEditText;
    public static EditText customerEditStatusEditText;
    public static EditText customerEditBioEditText;
    public static EditText customerEditLookingForEditText;


    public static String mood;
    public static String bio;
    public static String lookingFor;
    public static String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_update_profile);


        customerUpdateProfileToolbar = findViewById(R.id.customerUpdateProfileToolbar);
        customerEditProfileSaveButton = findViewById(R.id.customerEditProfileSaveButton);

        customerEditMoodEditText = findViewById(R.id.customerEditMoodEditText);
        customerEditStatusEditText = findViewById(R.id.customerEditStatusEditText);
        customerEditBioEditText = findViewById(R.id.customerEditBioEditText);
        customerEditLookingForEditText = findViewById(R.id.customerEditLookingForEditText);
        customerEditStatusEditText = findViewById(R.id.customerEditStatusEditText);

        customerEditMoodEditText.setText(mood);
        customerEditBioEditText.setText(bio);
        customerEditLookingForEditText.setText(lookingFor);
        customerEditStatusEditText.setText(status);

        customerUpdateProfileToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerEditProfileSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mood = customerEditMoodEditText.getText().toString();
                customerEditMoodEditText.setText(mood);
                bio = customerEditBioEditText.getText().toString();
                customerEditBioEditText.setText(bio);
                lookingFor = customerEditLookingForEditText.getText().toString();
                customerEditLookingForEditText.setText(lookingFor);
                status = customerEditStatusEditText.getText().toString();
                customerEditStatusEditText.setText(status);

                finish();
            }
        });

    }
}