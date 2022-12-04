package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

public class ProviderUpdateProfileActivity extends AppCompatActivity {

    private MaterialToolbar customerUpdateProfileToolbar;

    private MaterialButton providerEditProfileSaveButton;
    private MaterialButton providerEditProfilePictureButton;

    private TextInputEditText providerEditStatusEditText;
    private TextInputEditText providerEditMoodEditText;
    private TextInputEditText providerEditBioEditText;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private ValueEventListener listener;

    private ImageView providerProfilePictureImageView;
    private Uri imageUri;
    private static final int IMAGE_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_update_profile);

        customerUpdateProfileToolbar = findViewById(R.id.customerUpdateProfileToolbar);

        providerEditProfileSaveButton = findViewById(R.id.providerEditProfileSaveButton);
        providerEditProfilePictureButton = findViewById(R.id.providerEditProfilePictureButton);

        providerEditStatusEditText = findViewById(R.id.providerEditStatusEditText);
        providerEditMoodEditText = findViewById(R.id.providerEditMoodEditText);
        providerEditBioEditText = findViewById(R.id.providerEditBioEditText);

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUid);

        providerProfilePictureImageView = findViewById(R.id.providerProfilePictureImageView);

        customerUpdateProfileToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        providerEditProfileSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("profileInfo").child("status").setValue(providerEditStatusEditText.getText().toString());
                databaseReference.child("profileInfo").child("mood").setValue(providerEditMoodEditText.getText().toString());
                databaseReference.child("profileInfo").child("bio").setValue(providerEditBioEditText.getText().toString());

            }
        });

        providerEditProfilePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                providerEditStatusEditText.setText(snapshot.child("profileInfo").child("status").getValue().toString());
                providerEditMoodEditText.setText(snapshot.child("profileInfo").child("mood").getValue().toString());
                providerEditBioEditText.setText(snapshot.child("profileInfo").child("bio").getValue().toString());


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

    public void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST && data != null && resultCode == RESULT_OK){
            imageUri = data.getData();
            providerProfilePictureImageView.setImageURI(imageUri);
            uploadImage();
        }

    }

    public void uploadImage(){
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        storageReference = FirebaseStorage.getInstance().getReference().child("users").child(userUid).child(userUid + "pp.jpg");
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("Url", uri.toString());
                        FirebaseDatabase.getInstance().getReference().child("users").child(userUid).child("profileInfo").child("profileImageUri").setValue(uri.toString());

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProviderUpdateProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}