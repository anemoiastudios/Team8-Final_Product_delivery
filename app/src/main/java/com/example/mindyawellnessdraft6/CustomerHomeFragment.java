package com.example.mindyawellnessdraft6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
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
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;

public class CustomerHomeFragment extends Fragment {

    // private Button customerProfileLogoutButton;
    private MaterialToolbar customerHomeFragmentToolbar;

    private ImageView customerHomeFragmentProfileCardImageView;

    private TextView customerHomeFragmentCustomerNameTextView;
    private TextView customerHomeFragmentCustomerGenderTextView;
    private TextView customerHomeFragmentCustomerAgeTextView;
    private TextView customerHomeFragmentCustomerStatusTextView;
    private TextView moodTextView;
    private TextView bioTextView;
    private TextView lookingForTextView;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private ValueEventListener listener1;
    private ValueEventListener listener2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_home, container, false);

        customerHomeFragmentToolbar = view.findViewById(R.id.customerHomeFragmentToolbar);

        customerHomeFragmentProfileCardImageView = view.findViewById(R.id.customerHomeFragmentProfileCardImageView);

        customerHomeFragmentCustomerNameTextView = view.findViewById(R.id.customerHomeFragmentCustomerNameTextView);
        customerHomeFragmentCustomerGenderTextView = view.findViewById(R.id.customerHomeFragmentCustomerGenderTextView);
        customerHomeFragmentCustomerAgeTextView = view.findViewById(R.id.customerHomeFragmentCustomerAgeTextView);
        customerHomeFragmentCustomerStatusTextView = view.findViewById(R.id.customerHomeFragmentCustomerStatusTextView);
        moodTextView = view.findViewById(R.id.moodTextView);
        bioTextView = view.findViewById(R.id.bioTextView);
        lookingForTextView = view.findViewById(R.id.lookingForTextView);

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUid);

        // -----

        customerHomeFragmentToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;

                switch(item.getItemId()){
                    case R.id.customerSignOutButton:
                        FirebaseAuth.getInstance().signOut();

                        intent = new Intent(getActivity(), WelcomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();
                        return true;

                    case R.id.customerSettingsButton:
                        intent = new Intent(getActivity(), CustomerSettingsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        // Try to get profile image from the Firebase Storage. If it is null, then do nothing.
        /*
        if(databaseReference.child("profileImageUri").equals("")){
            customerHomeFragmentProfileCardImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_do_not_disturb_24));
        } else {
            try {
                File localFile = File.createTempFile("tempfile", ".jpg");

                storageReference = FirebaseStorage.getInstance().getReference().child("users").child(userUid).child(userUid + "pp.jpg");

                storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        customerHomeFragmentProfileCardImageView.setImageBitmap(bitmap);


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } catch(Exception f){
                f.printStackTrace();

            }

        }
         */

        /*
        try {
            storageReference = FirebaseStorage.getInstance().getReference().child("users").child(userUid).child(userUid + "pp.jpg");
            try {
                File localFile = File.createTempFile("tempfile", ".jpg");

                storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        customerHomeFragmentProfileCardImageView.setImageBitmap(bitmap);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch(Exception f){
                f.printStackTrace();
            }
        } catch (Exception e){
            customerHomeFragmentProfileCardImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_do_not_disturb_24));
        }

         */

        /*
        customerProfileLogoutButton = view.findViewById(R.id.customerProfileLogoutButton);

        customerProfileLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();

            }
        });

         */

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        /*
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUid);
         */

        String userUid = auth.getCurrentUser().getUid();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.child("profileInfo").child("profileImageUri").getValue().equals("")){
                    storageReference = FirebaseStorage.getInstance().getReference().child("users").child(userUid).child(userUid + "pp.jpg");

                    try {
                        File localFile = File.createTempFile("tempfile", ".jpg");

                        storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                customerHomeFragmentProfileCardImageView.setImageBitmap(bitmap);


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(getActivity(), "Make sure to set your profile picture!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch(Exception e){
                        customerHomeFragmentProfileCardImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_do_not_disturb_24));
                        // f.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /*
        try {
            storageReference = FirebaseStorage.getInstance().getReference().child("users").child(userUid).child(userUid + "pp.jpg");

            try {
                storageReference = FirebaseStorage.getInstance().getReference().child("users").child(userUid).child(userUid + "pp.jpg");
                File localFile = File.createTempFile("tempfile", ".jpg");

                storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        customerHomeFragmentProfileCardImageView.setImageBitmap(bitmap);


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "Set your profile picture!", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch(Exception e){
                customerHomeFragmentProfileCardImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_do_not_disturb_24));
                // f.printStackTrace();

            }

        } catch (Exception e){
            // customerHomeFragmentProfileCardImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_do_not_disturb_24));
        }
         */

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Change some client information from provider side. Then go to client and change some information. If automatically goes back to home fragment, there will be an issue
                // There is an error here when I change client information from provider side
                customerHomeFragmentCustomerNameTextView.setText(snapshot.child("privateInfo").child("firstName").getValue().toString() + " " + snapshot.child("privateInfo").child("lastName").getValue().toString());
                customerHomeFragmentCustomerGenderTextView.setText(snapshot.child("privateInfo").child("gender").getValue().toString());
                customerHomeFragmentCustomerAgeTextView.setText(snapshot.child("privateInfo").child("age").getValue().toString());

                customerHomeFragmentCustomerStatusTextView.setText(snapshot.child("profileInfo").child("status").getValue().toString());

                moodTextView.setText(snapshot.child("profileInfo").child("mood").getValue().toString());
                bioTextView.setText(snapshot.child("profileInfo").child("bio").getValue().toString());
                lookingForTextView.setText(snapshot.child("profileInfo").child("lookingFor").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}