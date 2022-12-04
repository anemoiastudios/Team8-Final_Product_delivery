package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerProviderProfileActivity extends AppCompatActivity {

    private MaterialToolbar customerProviderProfileMaterialToolbar;

    private ImageView customerProviderProfileCardImageView;

    private TextView customerProviderProfileProviderNameTextView;
    private TextView customerProviderProfileProviderTypeTextView;
    private TextView customerProviderProfileProviderStatusTextView;
    private TextView customerProviderMoodTextView;
    private TextView customerProviderBioTextView;

    private ImageButton customerProviderProfileBookmarkImageButton;
    private ImageButton customerProviderProfileAppointmentImageButton;
    private ImageButton customerProviderProfileMessageImageButton;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private DatabaseReference discussionsDatabaseReference;
    private StorageReference storageReference;

    private RecyclerView customerProviderProfileDiscussionsRecyclerView;

    private ValueEventListener listener;

    boolean providerIsBookmarked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_provider_profile);

        customerProviderProfileMaterialToolbar = findViewById(R.id.customerProviderProfileMaterialToolbar);

        customerProviderProfileMaterialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();

        customerProviderProfileCardImageView = findViewById(R.id.customerProviderProfileCardImageView);

        customerProviderProfileProviderNameTextView = findViewById(R.id.customerProviderProfileProviderNameTextView);
        customerProviderProfileProviderTypeTextView = findViewById(R.id.customerProviderProfileProviderTypeTextView);
        customerProviderProfileProviderStatusTextView = findViewById(R.id.customerProviderProfileProviderStatusTextView);
        customerProviderMoodTextView = findViewById(R.id.customerProviderMoodTextView);
        customerProviderBioTextView = findViewById(R.id.customerProviderBioTextView);

        customerProviderProfileBookmarkImageButton = findViewById(R.id.customerProviderProfileBookmarkImageButton);
        customerProviderProfileAppointmentImageButton = findViewById(R.id.customerProviderProfileAppointmentImageButton);
        customerProviderProfileMessageImageButton = findViewById(R.id.customerProviderProfileMessageImageButton);

        // Give the fields of the activity values
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            String providerUid = bundle.getString("uid");

            databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(providerUid);

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    customerProviderProfileProviderNameTextView.setText(snapshot.child("privateInfo").child("fullName").getValue().toString());
                    customerProviderProfileProviderTypeTextView.setText(snapshot.child("privateInfo").child("credential").getValue().toString());
                    customerProviderProfileProviderStatusTextView.setText(snapshot.child("profileInfo").child("status").getValue().toString());
                    customerProviderMoodTextView.setText(snapshot.child("profileInfo").child("mood").getValue().toString());
                    customerProviderBioTextView.setText(snapshot.child("profileInfo").child("bio").getValue().toString());

                    if(!snapshot.child("profileInfo").child("profileImageUri").getValue().toString().equals("")){
                        storageReference = FirebaseStorage.getInstance().getReference().child("users").child(providerUid).child(providerUid + "pp.jpg");

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
                                    Toast.makeText(CustomerProviderProfileActivity.this, "Make sure to set your profile picture!", Toast.LENGTH_SHORT).show();
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

        }

        // Get to determine the state of bookmarked for this provider
        DatabaseReference clientDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUid).child("bookmarkedProviderInfo");

        clientDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                providerIsBookmarked = false;

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String providerUid = bundle.getString("uid");

                    if(dataSnapshot.child("providerUid").getValue().toString().equals(providerUid)){
                        providerIsBookmarked = true;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Set the onClickListeners for all the buttons
        customerProviderProfileBookmarkImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(providerIsBookmarked == false){
                    // Add to bookmark if the the provider is not in the bookmark section
                    Toast.makeText(CustomerProviderProfileActivity.this, "Added", Toast.LENGTH_SHORT).show();

                    String providerUid = bundle.getString("uid");
                    DatabaseReference providerDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(providerUid);

                    providerDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String providerName;
                            String therapistType;
                            // String providerUid;

                            providerName = snapshot.child("privateInfo").child("fullName").getValue().toString();
                            therapistType = snapshot.child("privateInfo").child("credential").getValue().toString();

                            HashMap<String, Object> bookmarked = new HashMap<>();
                            Provider provider = new Provider(providerName, therapistType, providerUid);
                            bookmarked.put(providerUid, provider);

                            clientDatabaseReference.updateChildren(bookmarked);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } else {
                    Toast.makeText(CustomerProviderProfileActivity.this, "Removed", Toast.LENGTH_SHORT).show();

                    String providerUid = bundle.getString("uid");

                    clientDatabaseReference.child(providerUid).removeValue();
                }
            }
        });

        customerProviderProfileAppointmentImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String providerUid = bundle.getString("uid");

                Bundle bundle = new Bundle();
                bundle.putString("providerUid", providerUid);

                Intent intent = new Intent(CustomerProviderProfileActivity.this, CustomerSetAppointmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        customerProviderProfileMessageImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerProviderProfileActivity.this, CustomerChatWithProviderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        // Populate the discussions recyclerView
        discussionsDatabaseReference = FirebaseDatabase.getInstance().getReference().child("discussions");

        customerProviderProfileDiscussionsRecyclerView = findViewById(R.id.customerProviderProfileDiscussionsRecyclerView);

        ArrayList<Discussion> discussions = new ArrayList<>();
        CustomerProviderProfileDiscussionListRecyclerViewAdapter adapter = new CustomerProviderProfileDiscussionListRecyclerViewAdapter(CustomerProviderProfileActivity.this);
        adapter.setDiscussions(discussions);

        discussionsDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String discussionTitle = dataSnapshot.child("discussionTitle").getValue().toString();
                    String discussionDate = dataSnapshot.child("discussionDate").getValue().toString();
                    String discussionDescription = dataSnapshot.child("discussionDescription").getValue().toString();
                    String discussionProviderUid = dataSnapshot.child("discussionProviderUid").getValue().toString();
                    String discussionId = dataSnapshot.child("discussionId").getValue().toString();

                    String providerUid = bundle.getString("uid");

                    if(providerUid.equals(discussionProviderUid)){
                        Discussion discussion = new Discussion(discussionTitle, discussionDate, discussionDescription, discussionProviderUid, discussionId);
                        discussions.add(discussion);

                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*
        discussions.add(new Discussion("Alcoholics Anonymous", "10/4/22"));
        discussions.add(new Discussion("Help with Mental", "10/2/22"));
        discussions.add(new Discussion("Help with Sadness :(", "10/10/22"));

         */


        customerProviderProfileDiscussionsRecyclerView.setAdapter(adapter);
        customerProviderProfileDiscussionsRecyclerView.setLayoutManager(new LinearLayoutManager(CustomerProviderProfileActivity.this, LinearLayoutManager.HORIZONTAL, false));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}