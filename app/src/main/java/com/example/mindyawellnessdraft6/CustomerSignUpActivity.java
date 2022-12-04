package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class CustomerSignUpActivity extends AppCompatActivity {

    private TextInputEditText emailCustomerSignUpEditText;
    private TextInputEditText passwordCustomerSignUpEditText;
    private TextInputEditText reInputPasswordCustomerSignUpEditText;

    private MaterialButton continueCustomerSignUpButton;

    // private FirebaseAuth auth;
    // private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);

        emailCustomerSignUpEditText = findViewById(R.id.emailCustomerSignUpEditText);
        passwordCustomerSignUpEditText = findViewById(R.id.passwordCustomerSignUpEditText);
        reInputPasswordCustomerSignUpEditText = findViewById(R.id.reInputPasswordCustomerSignUpEditText);

        continueCustomerSignUpButton = findViewById(R.id.continueCustomerSignUpButton);

        // auth = FirebaseAuth.getInstance();
        // databaseReference = FirebaseDatabase.getInstance().getReference();

        continueCustomerSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailCustomerSignUpEditText.getText().toString();
                String password = passwordCustomerSignUpEditText.getText().toString();
                String rePassword = reInputPasswordCustomerSignUpEditText.getText().toString();

                FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        try {
                            boolean isNewUser = task.getResult().getSignInMethods().isEmpty();

                            if(isNewUser){
                                if(email.length() == 0 || password.length() < 5 || rePassword.length() < 5){
                                    Toast.makeText(CustomerSignUpActivity.this, "Have information inputted", Toast.LENGTH_SHORT).show();

                                } else if(!password.equals(rePassword)){
                                    Toast.makeText(CustomerSignUpActivity.this, "Passwords are not the same", Toast.LENGTH_SHORT).show();

                                } else {
                                    Intent intent = new Intent(CustomerSignUpActivity.this, CustomerEvaluationFormActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                    Bundle bundle = new Bundle();
                                    bundle.putString("email", email);
                                    bundle.putString("password", password);

                                    intent.putExtras(bundle);

                                    startActivity(intent);
                                    finish();

                                /*
                                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        HashMap<String, Object> h = new HashMap<>();

                                        h.put("email", email);
                                        h.put("userType", "CUSTOMER");
                                        h.put("uid", auth.getCurrentUser().getUid());

                                        databaseReference.child("Users").child(auth.getCurrentUser().getUid()).setValue(h).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(CustomerSignUpActivity.this, "Successful sign up", Toast.LENGTH_SHORT).show();

                                                    Intent intent = new Intent(CustomerSignUpActivity.this, CustomerEvaluationFormActivity.class);
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
                                        Toast.makeText(CustomerSignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                                 */
                                }


                            /*
                            Intent intent = new Intent(CustomerSignUpActivity.this, CustomerEvaluationFormActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                             */

                            } else {
                                // To check if email is already in use
                                Toast.makeText(CustomerSignUpActivity.this, "This email is already in use", Toast.LENGTH_SHORT).show();
                            }

                        } catch(Exception e){
                            // To check if email is badly formatted
                            Toast.makeText(CustomerSignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });




    }
}