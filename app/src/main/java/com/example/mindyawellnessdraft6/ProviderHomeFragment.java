package com.example.mindyawellnessdraft6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

public class ProviderHomeFragment extends Fragment {
    private MaterialToolbar providerHomeFragmentToolbar;

    private ImageView providerHomeFragmentProfileCardImageView;

    private TextView providerHomeFragmentProviderNameTextView;
    private TextView providerHomeFragmentProviderTypeTextView;
    private TextView providerHomeFragmentProviderStatusTextView;
    private TextView moodTextView;
    private TextView bioTextView;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private DatabaseReference discussionsDatabaseReference;
    private StorageReference storageReference;

    private RecyclerView providerHomeFragmentDiscussionsRecyclerView;

    private ValueEventListener listener1;
    private ValueEventListener listener2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_home, container, false);

        providerHomeFragmentToolbar = view.findViewById(R.id.providerHomeFragmentToolbar);

        providerHomeFragmentProfileCardImageView = view.findViewById(R.id.providerHomeFragmentProfileCardImageView);

        providerHomeFragmentProviderNameTextView = view.findViewById(R.id.providerHomeFragmentProviderNameTextView);
        providerHomeFragmentProviderTypeTextView = view.findViewById(R.id.providerHomeFragmentProviderTypeTextView);
        providerHomeFragmentProviderStatusTextView = view.findViewById(R.id.providerHomeFragmentProviderStatusTextView);
        moodTextView = view.findViewById(R.id.moodTextView);
        bioTextView = view.findViewById(R.id.bioTextView);

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUid);

        // -----

        providerHomeFragmentToolbar.setOnMenuItemClickListener(new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;

                switch(item.getItemId()){
                    case R.id.providerSignOutButton:
                        FirebaseAuth.getInstance().signOut();

                        intent = new Intent(getActivity(), WelcomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();
                        return true;
                    case R.id.providerSettingsButton:
                        intent = new Intent(getActivity(), ProviderSettingsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);



                }
                return false;
            }
        });

        discussionsDatabaseReference = FirebaseDatabase.getInstance().getReference().child("discussions");

        providerHomeFragmentDiscussionsRecyclerView = view.findViewById(R.id.providerHomeFragmentDiscussionsRecyclerView);

        ArrayList<Discussion> discussions = new ArrayList<>();
        CustomerProviderProfileDiscussionListRecyclerViewAdapter adapter = new CustomerProviderProfileDiscussionListRecyclerViewAdapter(getActivity());
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

                    if(userUid.equals(discussionProviderUid)){
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


        providerHomeFragmentDiscussionsRecyclerView.setAdapter(adapter);
        providerHomeFragmentDiscussionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        // Try to get profile image from the Firebase Storage. If it is null, then do nothing.





        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

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
                                providerHomeFragmentProfileCardImageView.setImageBitmap(bitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Make sure to set your profile picture!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch(Exception e){
                        providerHomeFragmentProfileCardImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_do_not_disturb_24));
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                providerHomeFragmentProviderNameTextView.setText(snapshot.child("privateInfo").child("fullName").getValue().toString());
                providerHomeFragmentProviderTypeTextView.setText(snapshot.child("privateInfo").child("credential").getValue().toString());

                providerHomeFragmentProviderStatusTextView.setText(snapshot.child("profileInfo").child("status").getValue().toString());
                moodTextView.setText(snapshot.child("profileInfo").child("mood").getValue().toString());
                bioTextView.setText(snapshot.child("profileInfo").child("bio").getValue().toString());

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