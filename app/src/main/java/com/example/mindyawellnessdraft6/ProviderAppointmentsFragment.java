package com.example.mindyawellnessdraft6;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProviderAppointmentsFragment extends Fragment {

    private MaterialToolbar providerAppointmentsToolbar;

    private RecyclerView providerRequestsRecyclerView;
    private RecyclerView providerAppointmentsRecyclerView;

    private FirebaseAuth auth;
    private DatabaseReference appointmentDatabaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_appointments, container, false);

        providerAppointmentsToolbar = view.findViewById(R.id.providerAppointmentsToolbar);

        providerAppointmentsToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;

                switch(item.getItemId()){
                    case R.id.providerCreateDiscussion:
                        intent = new Intent(getActivity(), ProviderCreateDiscussionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;
                    case R.id.providerAcceptDeclineDiscussion:
                        intent = new Intent(getActivity(), ProviderAppointmentAcceptDeclineActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);


                        return true;
                }
                return false;
            }
        });

        providerAppointmentsRecyclerView = view.findViewById(R.id.providerAppointmentsRecyclerView);

        /*
        providerRequestsRecyclerView = view.findViewById(R.id.providerRequestsRecyclerView);

        ArrayList<Appointment> customerRequests = new ArrayList<>();
        ProviderRequestRecyclerViewAdapter requestAdapter = new ProviderRequestRecyclerViewAdapter(getActivity());
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


        // customerRequests.add(new CustomerRequest("Customer Name 1", "10/14/22 5:40 AM"));
        // customerRequests.add(new CustomerRequest("Customer Name 2", "10/10/22 3:30 PM"));
        // customerRequests.add(new CustomerRequest("Customer Name 3", "9/19/22 5:50 PM"));
        // customerRequests.add(new CustomerRequest("Customer Name 4", "9/11/22 6:10 AM"));


        providerRequestsRecyclerView.setAdapter(requestAdapter);
        providerRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         */

        // -----


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();

        appointmentDatabaseReference = FirebaseDatabase.getInstance().getReference().child("appointments");

        ArrayList<Appointment> providerAppointments = new ArrayList<>();
        ProviderAppointmentsRecyclerViewAdapter appointmentsAdapter = new ProviderAppointmentsRecyclerViewAdapter(getActivity());
        appointmentsAdapter.setProviderAppointments(providerAppointments);

        appointmentDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                providerAppointments.clear();

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String appointmentTitle = dataSnapshot.child("appointmentTitle").getValue().toString();
                    String appointmentDate = dataSnapshot.child("appointmentDate").getValue().toString();
                    String appointmentTime = dataSnapshot.child("appointmentTime").getValue().toString();
                    String appointmentDescription = dataSnapshot.child("appointmentDescription").getValue().toString();
                    String customerUid = dataSnapshot.child("customerUid").getValue().toString();
                    String providerUid = dataSnapshot.child("providerUid").getValue().toString();
                    String providerAccepted = dataSnapshot.child("providerAccepted").getValue().toString();
                    String appointmentId = dataSnapshot.child("appointmentId").getValue().toString();

                    if(providerUid.equals(userUid) && providerAccepted.equals("1")){
                        Appointment appointment = new Appointment(appointmentTitle, appointmentDate, appointmentTime, appointmentDescription, customerUid, providerUid, appointmentId);
                        providerAppointments.add(appointment);

                    }

                    appointmentsAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*
        providerAppointments.add(new ProviderAppointment("Appointment Title 1", "Date 1", "Customer Name 1"));
        providerAppointments.add(new ProviderAppointment("Appointment Title 2", "Date 2", "Customer Name 2"));
        providerAppointments.add(new ProviderAppointment("Appointment Title 3", "Date 3", "Customer Name 3"));
        providerAppointments.add(new ProviderAppointment("Appointment Title 4", "Date 4", "Customer Name 4"));
        providerAppointments.add(new ProviderAppointment("Appointment Title 5", "Date 5", "Customer Name 5"));

         */

        providerAppointmentsRecyclerView.setAdapter(appointmentsAdapter);
        providerAppointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



    }
}