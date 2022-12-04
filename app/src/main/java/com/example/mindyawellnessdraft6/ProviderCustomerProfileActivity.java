package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.File;

public class ProviderCustomerProfileActivity extends AppCompatActivity {

    private MaterialToolbar providerCustomerProfileToolbar;

    private ImageView customerProviderProfileCardImageView;

    private TextView customerProviderProfileProviderNameTextView;
    private TextView customerProviderProfileProviderGenderTextView;
    private TextView customerProviderProfileProviderAgeTextView;
    private TextView customerProviderProfileProviderStatusTextView;

    private TextView providerCustomerMoodTextView;
    private TextView providerCustomerBioTextView;
    private TextView providerCustomerLookingForTextView;

    private ImageButton customerProviderProfileMessageImageButton;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_customer_profile);

        providerCustomerProfileToolbar = findViewById(R.id.providerCustomerProfileToolbar);

        customerProviderProfileCardImageView = findViewById(R.id.customerProviderProfileCardImageView);

        customerProviderProfileProviderNameTextView = findViewById(R.id.customerProviderProfileProviderNameTextView);
        customerProviderProfileProviderGenderTextView = findViewById(R.id.customerProviderProfileProviderGenderTextView);
        customerProviderProfileProviderAgeTextView = findViewById(R.id.customerProviderProfileProviderAgeTextView);
        customerProviderProfileProviderStatusTextView = findViewById(R.id.customerProviderProfileProviderStatusTextView);

        providerCustomerMoodTextView = findViewById(R.id.providerCustomerMoodTextView);
        providerCustomerBioTextView = findViewById(R.id.providerCustomerBioTextView);
        providerCustomerLookingForTextView = findViewById(R.id.providerCustomerLookingForTextView);

        customerProviderProfileMessageImageButton = findViewById(R.id.customerProviderProfileMessageImageButton);

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();

        Bundle bundle = getIntent().getExtras();
        String customerUid = bundle.getString("customerUid");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(customerUid);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                customerProviderProfileProviderNameTextView.setText(snapshot.child("privateInfo").child("firstName").getValue().toString() + " " + snapshot.child("privateInfo").child("lastName").getValue().toString());
                customerProviderProfileProviderGenderTextView.setText(snapshot.child("privateInfo").child("gender").getValue().toString());
                customerProviderProfileProviderAgeTextView.setText(snapshot.child("privateInfo").child("age").getValue().toString());

                customerProviderProfileProviderStatusTextView.setText(snapshot.child("profileInfo").child("status").getValue().toString());
                providerCustomerMoodTextView.setText(snapshot.child("profileInfo").child("mood").getValue().toString());
                providerCustomerBioTextView.setText(snapshot.child("profileInfo").child("bio").getValue().toString());
                providerCustomerLookingForTextView.setText(snapshot.child("profileInfo").child("lookingFor").getValue().toString());

                // Get profile picture from storage
                if(!snapshot.child("profileInfo").child("profileImageUri").getValue().toString().equals("")){
                    storageReference = FirebaseStorage.getInstance().getReference().child("users").child(customerUid).child(customerUid + "pp.jpg");

                    try {
                        File localFile = File.createTempFile("tempfile", ".jpg");

                        storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                customerProviderProfileCardImageView.setImageBitmap(bitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ProviderCustomerProfileActivity.this, "Make sure to set your profile picture!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch(Exception e){
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Set onClickListeners for the buttons
        providerCustomerProfileToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        providerCustomerProfileToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.customerEditPrivateInformationButton:
                        Bundle bundle = new Bundle();
                        bundle.putString("customerUid", customerUid);

                        Intent intent = new Intent(ProviderCustomerProfileActivity.this, ProviderCustomerEditInformation.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        return true;
                }

                return false;
            }
        });

        customerProviderProfileMessageImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProviderCustomerProfileActivity.this, ProviderChatWithCustomerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });








    }
}