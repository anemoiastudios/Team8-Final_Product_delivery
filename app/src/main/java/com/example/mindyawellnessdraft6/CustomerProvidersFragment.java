package com.example.mindyawellnessdraft6;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerProvidersFragment extends Fragment {
    private RecyclerView customerProviderBookmarkedRecyclerView;
    private RecyclerView customerProviderAllRecyclerView;

    private MaterialButton instantChattingButton;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_providers, container, false);

        instantChattingButton = view.findViewById(R.id.instantChattingButton);

        customerProviderBookmarkedRecyclerView = view.findViewById(R.id.customerProviderBookmarkedRecyclerView);
        customerProviderAllRecyclerView = view.findViewById(R.id.customerProviderAllRecyclerView);

        instantChattingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CustomerInstantChattingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        /*
        // Hardcoded for now
        // Populate the bookmarkedProviders based on customer bookmarked field in Firebase
        customerProviderBookmarkedRecyclerView = view.findViewById(R.id.customerProviderBookmarkedRecyclerView);

        ArrayList<Provider> bookmarkedProviders = new ArrayList<>();

        // During the loop, if not equal to 0, then display

        bookmarkedProviders.add(new Provider("Carl Chandlers", "LCSW", "1"));
        bookmarkedProviders.add(new Provider("Doug Carlson", "MFT", "1"));
        bookmarkedProviders.add(new Provider("Carl Chandlers", "LCSW", "1"));
        bookmarkedProviders.add(new Provider("Horatio Horatio", "LPC", "1"));
        bookmarkedProviders.add(new Provider("Barack Obama", "LCSW", "1"));

        CustomerProvidersCardListRecyclerViewAdapter bookmarkedAdapter = new CustomerProvidersCardListRecyclerViewAdapter(getActivity());
        bookmarkedAdapter.setProviders(bookmarkedProviders);

        customerProviderBookmarkedRecyclerView.setAdapter(bookmarkedAdapter);
        customerProviderBookmarkedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

         */

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();

        // Populate the bookmarkedProviders based on customer bookmarked field in Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUid).child("bookmarkedProviderInfo");
        // customerProviderBookmarkedRecyclerView = view.findViewById(R.id.customerProviderBookmarkedRecyclerView);

        ArrayList<Provider> bookmarkedProviders = new ArrayList<>();

        CustomerProvidersCardListRecyclerViewAdapter bookmarkedAdapter = new CustomerProvidersCardListRecyclerViewAdapter(getActivity());
        bookmarkedAdapter.setProviders(bookmarkedProviders);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    if(!dataSnapshot.child("providerUid").getValue().toString().equals("0")){
                        String providerName = dataSnapshot.child("providerName").getValue().toString();
                        String therapistType = dataSnapshot.child("therapistType").getValue().toString();
                        String providerUid = dataSnapshot.child("providerUid").getValue().toString();

                        Log.d("providerName", providerName);
                        Log.d("therapistType", therapistType);
                        Log.d("providerUid", providerUid);

                        bookmarkedProviders.add(new Provider(providerName, therapistType, providerUid));

                    }

                    bookmarkedAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        customerProviderBookmarkedRecyclerView.setAdapter(bookmarkedAdapter);
        customerProviderBookmarkedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        //-----

        // Populate the recommended providers
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        // customerProviderAllRecyclerView = view.findViewById(R.id.customerProviderAllRecyclerView);

        ArrayList<Provider> allProviders = new ArrayList<>();

        CustomerProvidersCardListRecyclerViewAdapter allAdapter = new CustomerProvidersCardListRecyclerViewAdapter(getActivity());
        allAdapter.setProviders(allProviders);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // allProviders.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if(dataSnapshot.child("credentials").child("userType").getValue().toString().equals("PROVIDER")){
                        // Toast.makeText(getActivity(), "It works", Toast.LENGTH_SHORT).show();
                        String providerName = dataSnapshot.child("privateInfo").child("fullName").getValue().toString();
                        String therapistType = dataSnapshot.child("privateInfo").child("credential").getValue().toString();
                        String providerUid = dataSnapshot.child("credentials").child("uid").getValue().toString();

                        Log.d("providerName", providerName);
                        Log.d("therapistType", therapistType);
                        Log.d("providerUid", providerUid);

                        allProviders.add(new Provider(providerName, therapistType, providerUid));

                    }

                    allAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        customerProviderAllRecyclerView.setAdapter(allAdapter);
        customerProviderAllRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        /*
        allProviders.add(new Provider("Jake Johnson", "MFT"));
        allProviders.add(new Provider("Adam Anderson", "PhD"));
        allProviders.add(new Provider("Carl Chandlers", "LCSW"));
        allProviders.add(new Provider("Betty Boo", "LPC"));
        allProviders.add(new Provider("Nikola Jokic", "PhD"));
        allProviders.add(new Provider("Selena Gomez", "LCSW"));
        allProviders.add(new Provider("Doug Carlson", "MFT"));
        allProviders.add(new Provider("Salt Papi", "PhD"));
        allProviders.add(new Provider("Horatio Horatio", "LPC"));
        allProviders.add(new Provider("Supa Hot", "MFT"));
        allProviders.add(new Provider("Barack Obama", "LCSW"));
         */


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}