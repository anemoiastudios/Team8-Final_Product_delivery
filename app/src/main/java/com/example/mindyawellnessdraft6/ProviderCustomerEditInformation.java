package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProviderCustomerEditInformation extends AppCompatActivity {

    private MaterialToolbar providerCustomerPrivateInformationToolbar;

    private EditText customerEditFirstNameEditText;
    private EditText customerEditLastNameEditText;
    private EditText customerEditAgeEditText;
    private EditText customerEditAddressEditText;
    private EditText customerEditZipCodeEditText;
    private EditText customerEditCityEditText;
    private EditText customerEditStateEditText;
    private EditText customerEditPhoneNumberEditText;

    private AutoCompleteTextView customerEditGenderAutoCompleteTextView;

    private EditText customerEditDateOfBirthEditText;
    private EditText customerEditHeightEditText;
    private EditText customerEditWeightEditText;
    private EditText customerEditBMIEditText;
    private EditText customerEditBloodPressureEditText;
    private EditText customerEditInsuranceCompanyNameEditText;
    private EditText customerEditInsuranceIDEditText;

    private EditText customerEditAllergiesEditText;
    private EditText customerEditMedicalConditionsEditText;
    private EditText customerEditMedicationsEditText;
    private EditText customerEditSymptomsEditText;
    private EditText customerEditMedicalHistoryEditText;
    private EditText doctorsNotesTextInputEditText;

    private DatabaseReference databaseReference;

    private ValueEventListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_customer_edit_information);

        providerCustomerPrivateInformationToolbar = findViewById(R.id.providerCustomerPrivateInformationToolbar);

        customerEditFirstNameEditText = findViewById(R.id.customerEditFirstNameEditText);
        customerEditLastNameEditText = findViewById(R.id.customerEditLastNameEditText);
        customerEditAgeEditText = findViewById(R.id.customerEditAgeEditText);
        customerEditAddressEditText = findViewById(R.id.customerEditAddressEditText);
        customerEditZipCodeEditText = findViewById(R.id.customerEditZipCodeEditText);
        customerEditCityEditText = findViewById(R.id.customerEditCityEditText);
        customerEditStateEditText = findViewById(R.id.customerEditStateEditText);
        customerEditPhoneNumberEditText = findViewById(R.id.customerEditPhoneNumberEditText);
        customerEditGenderAutoCompleteTextView = findViewById(R.id.customerEditGenderAutoCompleteTextView);

        String[] genders = {"Male", "Female", "None"};
        ArrayAdapter<String> gendersAdapter = new ArrayAdapter<>(ProviderCustomerEditInformation.this, R.layout.list_gender, genders);
        customerEditGenderAutoCompleteTextView.setAdapter(gendersAdapter);

        customerEditDateOfBirthEditText = findViewById(R.id.customerEditDateOfBirthEditText);
        customerEditHeightEditText = findViewById(R.id.customerEditHeightEditText);
        customerEditWeightEditText = findViewById(R.id.customerEditWeightEditText);
        customerEditBMIEditText = findViewById(R.id.customerEditBMIEditText);
        customerEditBloodPressureEditText = findViewById(R.id.customerEditBloodPressureEditText);
        customerEditInsuranceCompanyNameEditText = findViewById(R.id.customerEditInsuranceCompanyNameEditText);
        customerEditInsuranceIDEditText = findViewById(R.id.customerEditInsuranceIDEditText);

        customerEditAllergiesEditText = findViewById(R.id.customerEditAllergiesEditText);
        customerEditMedicalConditionsEditText = findViewById(R.id.customerEditMedicalConditionsEditText);
        customerEditMedicationsEditText = findViewById(R.id.customerEditMedicationsEditText);
        customerEditSymptomsEditText = findViewById(R.id.customerEditSymptomsEditText);
        customerEditMedicalHistoryEditText = findViewById(R.id.customerEditMedicalHistoryEditText);
        doctorsNotesTextInputEditText = findViewById(R.id.doctorsNotesTextInputEditText);

        providerCustomerPrivateInformationToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        providerCustomerPrivateInformationToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.customerEditPrivateInformationEntriesButton:
                        // Save the new data
                        databaseReference.child("privateInfo").child("firstName").setValue(customerEditFirstNameEditText.getText().toString());
                        databaseReference.child("privateInfo").child("lastName").setValue(customerEditLastNameEditText.getText().toString());
                        databaseReference.child("privateInfo").child("age").setValue(customerEditAgeEditText.getText().toString());
                        databaseReference.child("privateInfo").child("address").setValue(customerEditAddressEditText.getText().toString());
                        databaseReference.child("privateInfo").child("zipCode").setValue(customerEditZipCodeEditText.getText().toString());

                        databaseReference.child("privateInfo").child("city").setValue(customerEditCityEditText.getText().toString());
                        databaseReference.child("privateInfo").child("state").setValue(customerEditStateEditText.getText().toString());
                        databaseReference.child("privateInfo").child("phoneNumber").setValue(customerEditPhoneNumberEditText.getText().toString());

                        databaseReference.child("privateInfo").child("gender").setValue(customerEditGenderAutoCompleteTextView.getText().toString());

                        databaseReference.child("privateInfo").child("dateOfBirth").setValue(customerEditDateOfBirthEditText.getText().toString());
                        databaseReference.child("privateInfo").child("height").setValue(customerEditHeightEditText.getText().toString());
                        databaseReference.child("privateInfo").child("weight").setValue(customerEditWeightEditText.getText().toString());
                        databaseReference.child("privateInfo").child("BMI").setValue(customerEditBMIEditText.getText().toString());
                        databaseReference.child("privateInfo").child("bloodPressure").setValue(customerEditBloodPressureEditText.getText().toString());
                        databaseReference.child("privateInfo").child("insuranceCompany").setValue(customerEditInsuranceCompanyNameEditText.getText().toString());
                        databaseReference.child("privateInfo").child("insuranceID").setValue(customerEditInsuranceIDEditText.getText().toString());

                        databaseReference.child("privateInfo").child("allergies").setValue(customerEditAllergiesEditText.getText().toString());
                        databaseReference.child("privateInfo").child("conditions").setValue(customerEditMedicalConditionsEditText.getText().toString());
                        databaseReference.child("privateInfo").child("medications").setValue(customerEditMedicationsEditText.getText().toString());
                        databaseReference.child("privateInfo").child("symptoms").setValue(customerEditSymptomsEditText.getText().toString());
                        databaseReference.child("privateInfo").child("medicalHistory").setValue(customerEditMedicalHistoryEditText.getText().toString());
                        databaseReference.child("privateInfo").child("doctorsNote").setValue(doctorsNotesTextInputEditText.getText().toString());

                        // Make sure doctor's notes appears properly in the client's side

                        /*
                        Intent intent = new Intent(ProviderCustomerEditInformation.this, ProviderMainActivity.class);
                        startActivity(intent);
                         */
                        // finish();

                        return true;

                }

                return false;
            }
        });

        // Get the customerUid from the bundle
        Bundle bundle = getIntent().getExtras();
        String customerUid = bundle.getString("customerUid");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(customerUid);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                customerEditFirstNameEditText.setText(snapshot.child("privateInfo").child("firstName").getValue().toString());
                customerEditLastNameEditText.setText(snapshot.child("privateInfo").child("lastName").getValue().toString());
                customerEditAgeEditText.setText(snapshot.child("privateInfo").child("age").getValue().toString());
                customerEditAddressEditText.setText(snapshot.child("privateInfo").child("address").getValue().toString());
                customerEditZipCodeEditText.setText(snapshot.child("privateInfo").child("zipCode").getValue().toString());
                customerEditCityEditText.setText(snapshot.child("privateInfo").child("city").getValue().toString());
                customerEditStateEditText.setText(snapshot.child("privateInfo").child("state").getValue().toString());
                customerEditPhoneNumberEditText.setText(snapshot.child("privateInfo").child("phoneNumber").getValue().toString());

                if(snapshot.child("privateInfo").child("gender").getValue().toString().equals("Male")){
                    customerEditGenderAutoCompleteTextView.setText(gendersAdapter.getItem(0), false);

                } else if (snapshot.child("privateInfo").child("gender").getValue().toString().equals("Female")){
                    customerEditGenderAutoCompleteTextView.setText(gendersAdapter.getItem(1), false);

                } else {
                    customerEditGenderAutoCompleteTextView.setText(gendersAdapter.getItem(2), false);
                }

                customerEditDateOfBirthEditText.setText(snapshot.child("privateInfo").child("dateOfBirth").getValue().toString());
                customerEditHeightEditText.setText(snapshot.child("privateInfo").child("height").getValue().toString());
                customerEditWeightEditText.setText(snapshot.child("privateInfo").child("weight").getValue().toString());
                customerEditBMIEditText.setText(snapshot.child("privateInfo").child("BMI").getValue().toString());
                customerEditBloodPressureEditText.setText(snapshot.child("privateInfo").child("bloodPressure").getValue().toString());
                customerEditInsuranceCompanyNameEditText.setText(snapshot.child("privateInfo").child("insuranceCompany").getValue().toString());
                customerEditInsuranceIDEditText.setText(snapshot.child("privateInfo").child("insuranceID").getValue().toString());

                customerEditAllergiesEditText.setText(snapshot.child("privateInfo").child("allergies").getValue().toString());
                customerEditMedicalConditionsEditText.setText(snapshot.child("privateInfo").child("conditions").getValue().toString());
                customerEditMedicationsEditText.setText(snapshot.child("privateInfo").child("medications").getValue().toString());
                customerEditSymptomsEditText.setText(snapshot.child("privateInfo").child("symptoms").getValue().toString());
                customerEditMedicalHistoryEditText.setText(snapshot.child("privateInfo").child("medicalHistory").getValue().toString());
                doctorsNotesTextInputEditText.setText(snapshot.child("privateInfo").child("doctorsNote").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}