package com.example.mindyawellnessdraft6;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProviderCustomersFragment extends Fragment {

    private ListView providerCustomerListView;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private DatabaseReference customerDataReference;
    private MaterialButton instantChattingButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_customers, container, false);

        providerCustomerListView = view.findViewById(R.id.providerCustomerListView);
        instantChattingButton = view.findViewById(R.id.instantChattingButton);


        instantChattingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CustomerInstantChattingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        // Go to Database, and find each client using FirebaseAuth and FirebaseDatabase.
        // For each client go into bookmarked section.
        // If the providerUid (currentUserUid) is in that client's bookmark folder, than we add the customer to array

        ArrayList<Customer> customers = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_activated_1, customers);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    // Check if the uid belongs to a customer
                    if(dataSnapshot.child("credentials").child("userType").getValue().toString().equals("CUSTOMER")){
                        // Check to see if the customer's bookmark includes the provider
                        String customerUid = dataSnapshot.child("credentials").child("uid").getValue().toString();

                        customerDataReference = FirebaseDatabase.getInstance().getReference().child("users").child(customerUid).child("bookmarkedProviderInfo");

                        customerDataReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                for(DataSnapshot dataSnapshot1: snapshot1.getChildren()){
                                    // Have if statement here
                                    if(dataSnapshot1.child("providerUid").getValue().toString().equals(userUid)){
                                        String firstName = dataSnapshot.child("privateInfo").child("firstName").getValue().toString();
                                        String lastName = dataSnapshot.child("privateInfo").child("lastName").getValue().toString();
                                        String fullName = firstName + " " + lastName;

                                        Customer customer = new Customer(fullName, customerUid);
                                        customers.add(customer);
                                    }

                                    adapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*
        customers.add("Customer 1");
        customers.add("Customer 2");
        customers.add("Customer 3");
        customers.add("Customer 4");
        customers.add("Customer 5");
        customers.add("Customer 6");
        customers.add("Customer 7");

         */

        providerCustomerListView.setAdapter(adapter);

        // -----

        // Set onClickListener to providerCustomerListView such that when click, goes to the profile card with the correct information
        providerCustomerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(getActivity(), customers.get(i).getCustomerName(), Toast.LENGTH_SHORT).show();

                String customerUid = customers.get(i).getCustomerUid();
                Bundle bundle = new Bundle();
                bundle.putString("customerUid", customerUid);

                Intent intent = new Intent(getActivity(), ProviderCustomerProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });





    }
}