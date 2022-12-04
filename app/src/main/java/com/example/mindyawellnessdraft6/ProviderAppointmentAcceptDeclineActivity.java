package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProviderAppointmentAcceptDeclineActivity extends AppCompatActivity {

    private MaterialToolbar customerSetAppointmentToolbar;

    private RecyclerView providerRequestsRecyclerView;

    private FirebaseAuth auth;
    private DatabaseReference appointmentDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_appointment_accept_decline);

        customerSetAppointmentToolbar = findViewById(R.id.customerSetAppointmentToolbar);

        customerSetAppointmentToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();

        appointmentDatabaseReference = FirebaseDatabase.getInstance().getReference().child("appointments");

        providerRequestsRecyclerView = findViewById(R.id.providerRequestsRecyclerView);

        ArrayList<Appointment> customerRequests = new ArrayList<>();
        ProviderRequestRecyclerViewAdapter requestAdapter = new ProviderRequestRecyclerViewAdapter(ProviderAppointmentAcceptDeclineActivity.this);
        requestAdapter.setCustomerRequests(customerRequests);

        // Create a new tab in appointments page where you accept or decline there instead of on the same page
        // Change to singleValueEventListener?
        // Remove customerRequest.clear?
        appointmentDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                customerRequests.clear();

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    String appointmentTitle = dataSnapshot.child("appointmentTitle").getValue().toString();
                    String appointmentDate = dataSnapshot.child("appointmentDate").getValue().toString();
                    String appointmentTime = dataSnapshot.child("appointmentTime").getValue().toString();
                    String appointmentDescription = dataSnapshot.child("appointmentDescription").getValue().toString();
                    String customerUid = dataSnapshot.child("customerUid").getValue().toString();
                    String providerUid = dataSnapshot.child("providerUid").getValue().toString();
                    String providerAccepted = dataSnapshot.child("providerAccepted").getValue().toString();
                    String appointmentId = dataSnapshot.child("appointmentId").getValue().toString();

                    if(providerUid.equals(userUid) && providerAccepted.equals("0")){
                        Appointment appointment = new Appointment(appointmentTitle, appointmentDate, appointmentTime, appointmentDescription, customerUid, providerUid, appointmentId);
                        customerRequests.add(appointment);
                    }

                    requestAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*
        customerRequests.add(new CustomerRequest("Customer Name 1", "10/14/22 5:40 AM"));
        customerRequests.add(new CustomerRequest("Customer Name 2", "10/10/22 3:30 PM"));
        customerRequests.add(new CustomerRequest("Customer Name 3", "9/19/22 5:50 PM"));
        customerRequests.add(new CustomerRequest("Customer Name 4", "9/11/22 6:10 AM"));

         */

        providerRequestsRecyclerView.setAdapter(requestAdapter);
        providerRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(ProviderAppointmentAcceptDeclineActivity.this));


    }
}