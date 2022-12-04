package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class DiscussionEntryActivity extends AppCompatActivity {

    private MaterialToolbar discussionToolbar;

    private TextView discussionTitle;
    private TextView discussionDate;
    private TextView discussionProvider;
    private TextView discussionDescription;

    private DatabaseReference discussionDatabaseReference;
    private DatabaseReference providerDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_entry);

        discussionToolbar = findViewById(R.id.discussionToolbar);

        discussionToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        discussionTitle = findViewById(R.id.discussionTitle);
        discussionDate = findViewById(R.id.discussionDate);
        discussionProvider = findViewById(R.id.discussionProvider);
        discussionDescription = findViewById(R.id.discussionDescription);

        Bundle bundle = getIntent().getExtras();
        String discussionId = bundle.getString("discussionId");

        discussionDatabaseReference = FirebaseDatabase.getInstance().getReference().child("discussions").child(discussionId);

        discussionDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                discussionTitle.setText(snapshot.child("discussionTitle").getValue().toString());
                discussionDate.setText(snapshot.child("discussionDate").getValue().toString());

                providerDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(snapshot.child("discussionProviderUid").getValue().toString());

                providerDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        discussionProvider.setText("Proctored by " + snapshot.child("privateInfo").child("fullName").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                discussionDescription.setText(snapshot.child("discussionDescription").getValue().toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}