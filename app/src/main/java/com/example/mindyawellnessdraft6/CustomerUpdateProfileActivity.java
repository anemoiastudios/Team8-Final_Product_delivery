package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

import java.io.ByteArrayOutputStream;

public class CustomerUpdateProfileActivity extends AppCompatActivity {

    private MaterialToolbar customerUpdateProfileToolbar;

    private MaterialButton customerEditProfileSaveButton;
    private MaterialButton customerEditProfilePictureButton;

    private TextInputEditText customerEditStatusEditText;
    private TextInputEditText customerEditMoodEditText;
    private TextInputEditText customerEditBioEditText;
    private TextInputEditText customerEditLookingForEditText;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private ValueEventListener listener;

    private ImageView customerProfilePictureImageView;
    private Uri imageUri;
    private static final int IMAGE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_update_profile);

        customerUpdateProfileToolbar = findViewById(R.id.customerUpdateProfileToolbar);
        customerEditProfileSaveButton = findViewById(R.id.customerEditProfileSaveButton);
        customerEditProfilePictureButton = findViewById(R.id.customerEditProfilePictureButton);

        customerEditStatusEditText = findViewById(R.id.customerEditStatusEditText);
        customerEditMoodEditText = findViewById(R.id.customerEditMoodEditText);
        customerEditBioEditText = findViewById(R.id.customerEditBioEditText);
        customerEditLookingForEditText = findViewById(R.id.customerEditLookingForEditText);

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUid);

        customerProfilePictureImageView = findViewById(R.id.customerProfilePictureImageView);

        customerUpdateProfileToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerEditProfileSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("profileInfo").child("status").setValue(customerEditStatusEditText.getText().toString());
                databaseReference.child("profileInfo").child("mood").setValue(customerEditMoodEditText.getText().toString());
                databaseReference.child("profileInfo").child("bio").setValue(customerEditBioEditText.getText().toString());
                databaseReference.child("profileInfo").child("lookingFor").setValue(customerEditLookingForEditText.getText().toString());
            }

        });

        customerEditProfilePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                customerEditStatusEditText.setText(snapshot.child("profileInfo").child("status").getValue().toString());
                customerEditMoodEditText.setText(snapshot.child("profileInfo").child("mood").getValue().toString());
                customerEditBioEditText.setText(snapshot.child("profileInfo").child("bio").getValue().toString());
                customerEditLookingForEditText.setText(snapshot.child("profileInfo").child("lookingFor").getValue().toString());
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
            customerProfilePictureImageView.setImageURI(imageUri);
            uploadImage();

        }
    }

    /*
    public String getFileExtension(Uri imageUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));

    }

     */

    public void uploadImage(){
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        storageReference = FirebaseStorage.getInstance().getReference().child("users").child(userUid).child(userUid + "pp.jpg");
        // storageReference = FirebaseStorage.getInstance().getReference().child("users").child(userUid).child(userUid + "pp." + getFileExtension(imageUri));
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("Url: ", uri.toString());

                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("profileInfo").child("profileImageUri").setValue(uri.toString());
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CustomerUpdateProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}