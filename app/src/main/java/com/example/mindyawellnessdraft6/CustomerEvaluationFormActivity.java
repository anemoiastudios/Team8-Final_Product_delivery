package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerEvaluationFormActivity extends AppCompatActivity {

    private TextInputEditText firstNamePEEditText;
    private TextInputEditText lastNamePEEditText;
    private TextInputEditText agePEEditText;
    private TextInputEditText addressPEEditText;
    private TextInputEditText zipCodePEEditText;
    private TextInputEditText cityPEEditText;
    private TextInputEditText statePEEditText;
    private TextInputEditText phoneNumberPEEditText;

    private TextInputLayout genderPETextInputLayout;
    private AutoCompleteTextView genderPEAutoCompleteTextView;

    private TextInputEditText dateOfBirthPEEditText;
    private TextInputEditText heightPEEditText;
    private TextInputEditText weightPEEditText;
    private TextInputEditText BMIPEEditText;
    private TextInputEditText bloodPressurePETextInputLayoutPEEditText;
    private TextInputEditText insuranceCompanyPETextInputLayoutPEEditText;
    private TextInputEditText insuranceIDPETextInputLayoutPEEditText;

    private TextInputEditText allergiesPETextInputLayoutPEEditText;
    private TextInputEditText conditionsPETextInputLayoutPEEditText;
    private TextInputEditText medicationsPETextInputLayoutPEEditText;
    private TextInputEditText symptomsPETextInputLayoutPEEditText;

    private MaterialButton finishEvaluationFormButton;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    // private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_evaluation_form);

        firstNamePEEditText = findViewById(R.id.firstNamePEEditText);
        lastNamePEEditText = findViewById(R.id.lastNamePEEditText);
        agePEEditText = findViewById(R.id.agePEEditText);
        addressPEEditText = findViewById(R.id.addressPEEditText);
        zipCodePEEditText = findViewById(R.id.zipCodePEEditText);
        cityPEEditText = findViewById(R.id.cityPEEditText);
        statePEEditText = findViewById(R.id.statePEEditText);
        phoneNumberPEEditText = findViewById(R.id.phoneNumberPEEditText);

        genderPETextInputLayout = findViewById(R.id.genderPETextInputLayout);
        genderPEAutoCompleteTextView = findViewById(R.id.genderPEAutoCompleteTextView);

        String[] genders = {"Male", "Female", "None"};
        ArrayAdapter<String> gendersAdapter = new ArrayAdapter<>(CustomerEvaluationFormActivity.this, R.layout.list_gender, genders);
        genderPEAutoCompleteTextView.setAdapter(gendersAdapter);

        dateOfBirthPEEditText = findViewById(R.id.dateOfBirthPEEditText);
        heightPEEditText = findViewById(R.id.heightPEEditText);
        weightPEEditText = findViewById(R.id.weightPEEditText);
        BMIPEEditText = findViewById(R.id.BMIPEEditText);
        bloodPressurePETextInputLayoutPEEditText = findViewById(R.id.bloodPressurePETextInputLayoutPEEditText);
        insuranceCompanyPETextInputLayoutPEEditText = findViewById(R.id.insuranceCompanyPETextInputLayoutPEEditText);
        insuranceIDPETextInputLayoutPEEditText = findViewById(R.id.insuranceIDPETextInputLayoutPEEditText);

        allergiesPETextInputLayoutPEEditText = findViewById(R.id.allergiesPETextInputLayoutPEEditText);
        conditionsPETextInputLayoutPEEditText = findViewById(R.id.conditionsPETextInputLayoutPEEditText);
        medicationsPETextInputLayoutPEEditText = findViewById(R.id.medicationsPETextInputLayoutPEEditText);
        symptomsPETextInputLayoutPEEditText = findViewById(R.id.symptomsPETextInputLayoutPEEditText);


        // -----

        finishEvaluationFormButton = findViewById(R.id.finishEvaluationFormButton);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // storageReference = FirebaseStorage.getInstance().getReference();

        finishEvaluationFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getIntent().getExtras();

                String email = bundle.getString("email");
                String password = bundle.getString("password");

                String firstName = firstNamePEEditText.getText().toString();
                String lastName = lastNamePEEditText.getText().toString();
                String age = agePEEditText.getText().toString();
                String address = addressPEEditText.getText().toString();
                String zipCode = zipCodePEEditText.getText().toString();
                String city = cityPEEditText.getText().toString();
                String state = statePEEditText.getText().toString();
                String phoneNumber = phoneNumberPEEditText.getText().toString();

                String gender = genderPEAutoCompleteTextView.getText().toString();

                String dateOfBirth = dateOfBirthPEEditText.getText().toString();
                String height = heightPEEditText.getText().toString();
                String weight = weightPEEditText.getText().toString();
                String BMI = BMIPEEditText.getText().toString();
                String bloodPressure = bloodPressurePETextInputLayoutPEEditText.getText().toString();
                String insuranceCompany = insuranceCompanyPETextInputLayoutPEEditText.getText().toString();
                String insuranceID = insuranceIDPETextInputLayoutPEEditText.getText().toString();

                String allergies = allergiesPETextInputLayoutPEEditText.getText().toString();
                String conditions = conditionsPETextInputLayoutPEEditText.getText().toString();
                String medications = medicationsPETextInputLayoutPEEditText.getText().toString();
                String symptoms = symptomsPETextInputLayoutPEEditText.getText().toString();

                if(firstName.length() == 0 || lastName.length() == 0 || age.length() == 0 || address.length() == 0 || zipCode.length() == 0 || city.length() == 0 || state.length() == 0 || phoneNumber.length() == 0 || gender.length() == 0){
                    Toast.makeText(CustomerEvaluationFormActivity.this, "Make sure you enter something in for each", Toast.LENGTH_SHORT).show();

                } else if(dateOfBirth.length () == 0 || height.length () == 0 || weight.length () == 0 || BMI.length () == 0 || bloodPressure.length () == 0){
                    Toast.makeText(CustomerEvaluationFormActivity.this, "Make sure information for measurements is filled out", Toast.LENGTH_SHORT).show();

                } else if(allergies.length () == 0 || conditions.length () == 0 || medications.length () == 0 || symptoms.length () == 0){
                    Toast.makeText(CustomerEvaluationFormActivity.this, "Make sure allergies, conditions, medications and symptoms are filled out", Toast.LENGTH_SHORT).show();

                } else {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            HashMap<String, Object> credentials = new HashMap<>();

                            credentials.put("email", email);
                            credentials.put("password", password);
                            credentials.put("userType", "CUSTOMER");
                            credentials.put("uid", firebaseAuth.getCurrentUser().getUid());

                            // Add to the database of the current user.

                            HashMap<String, Object> profileInfo = new HashMap<>();
                            profileInfo.put("status", "");
                            profileInfo.put("mood", "");
                            profileInfo.put("bio", "");
                            profileInfo.put("lookingFor", "");
                            profileInfo.put("profileImageUri", "");

                            HashMap<String, Object> privateInfo = new HashMap<>();

                            privateInfo.put("firstName", firstName);
                            privateInfo.put("lastName", lastName);
                            privateInfo.put("age", age);
                            privateInfo.put("address", address);
                            privateInfo.put("zipCode", zipCode);
                            privateInfo.put("city", city);
                            privateInfo.put("state", state);
                            privateInfo.put("phoneNumber", phoneNumber);
                            privateInfo.put("gender", gender);
                            privateInfo.put("dateOfBirth", dateOfBirth);
                            privateInfo.put("height", height);
                            privateInfo.put("weight", weight);
                            privateInfo.put("BMI", BMI);
                            privateInfo.put("bloodPressure", bloodPressure);

                            if(insuranceCompany.isEmpty()){
                                privateInfo.put("insuranceCompany", "");
                            } else {
                                privateInfo.put("insuranceCompany", insuranceCompany);
                            }

                            if(insuranceID.isEmpty()){
                                privateInfo.put("insuranceID", "");
                            } else {
                                privateInfo.put("insuranceID", insuranceID);
                            }

                            privateInfo.put("allergies", allergies);
                            privateInfo.put("conditions", conditions);
                            privateInfo.put("medications", medications);
                            privateInfo.put("symptoms", symptoms);
                            privateInfo.put("medicalHistory", "");
                            privateInfo.put("doctorsNote", "");

                            // Appointments
                            HashMap<String, Object> appointmentInfo = new HashMap<>();
                            CustomerAppointment testAppointment = new CustomerAppointment("Welcome","01/23/22","Mind Ya Wellness","0");
                            appointmentInfo.put("0", testAppointment);

                            // Bookmarked Providers
                            HashMap<String, Object> bookmarkedProviderInfo = new HashMap<>();
                            Provider mindYaWellnessProvider = new Provider("Mind Ya Wellness", "Welcome", "0");
                            bookmarkedProviderInfo.put("0", mindYaWellnessProvider);

                            // -----

                            // storageReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child(firebaseAuth.getCurrentUser().getUid() + "pp.jpeg");

                            databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("credentials").setValue(credentials);

                            databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("profileInfo").setValue(profileInfo);

                            databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("appointmentInfo").setValue(appointmentInfo);

                            databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("bookmarkedProviderInfo").setValue(bookmarkedProviderInfo);

                            databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("privateInfo").setValue(privateInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(CustomerEvaluationFormActivity.this, "Successfully signed up as customer", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(CustomerEvaluationFormActivity.this, CustomerMainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();

                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CustomerEvaluationFormActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

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