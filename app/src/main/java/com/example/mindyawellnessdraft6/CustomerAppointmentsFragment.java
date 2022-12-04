package com.example.mindyawellnessdraft6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerAppointmentsFragment extends Fragment {

    private RecyclerView customerAppointmentsRecyclerView;

    private FirebaseAuth auth;
    private DatabaseReference appointmentsDatabaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_appointments, container, false);

        auth = FirebaseAuth.getInstance();
        String clientUid = auth.getCurrentUser().getUid();

        appointmentsDatabaseReference = FirebaseDatabase.getInstance().getReference().child("appointments");

        customerAppointmentsRecyclerView = view.findViewById(R.id.customerAppointmentsRecyclerView);

        ArrayList<Appointment> customerAppointments = new ArrayList<>();
        CustomerAppointmentsRecyclerViewAdapter adapter = new CustomerAppointmentsRecyclerViewAdapter(getActivity());
        adapter.setCustomerAppointments(customerAppointments);

        appointmentsDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String appointmentTitle = dataSnapshot.child("appointmentTitle").getValue().toString();
                    String appointmentDate = dataSnapshot.child("appointmentDate").getValue().toString();
                    String appointmentTime = dataSnapshot.child("appointmentTime").getValue().toString();
                    String appointmentDescription = dataSnapshot.child("appointmentDescription").getValue().toString();
                    String customerUid = dataSnapshot.child("customerUid").getValue().toString();
                    String providerUid = dataSnapshot.child("providerUid").getValue().toString();
                    String providerAccepted = dataSnapshot.child("providerAccepted").getValue().toString();
                    String appointmentId = dataSnapshot.child("appointmentId").getValue().toString();

                    if(clientUid.equals(customerUid) && providerAccepted.equals("1")){
                        Appointment appointment = new Appointment(appointmentTitle, appointmentDate, appointmentTime, appointmentDescription, customerUid, providerUid, appointmentId);
                        customerAppointments.add(appointment);

                    }

                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*
        customerAppointments.add(new CustomerAppointment("Help with Something", "10/14/22 9:30 PM", "Doug Johnson"));
        customerAppointments.add(new CustomerAppointment("GROUP DISCUSSION: Alcoholics Anonymous", "10/15/22 10:45 AM", "Johnny Cash"));
        customerAppointments.add(new CustomerAppointment("Help with Something 2", "10/01/22 9:30 PM", "Selena Gomez"));
        customerAppointments.add(new CustomerAppointment("GROUP DISCUSSION: Trauma", "9/5/22 11:30 PM", "John Carmack"));
        customerAppointments.add(new CustomerAppointment("GROUP DISCUSSION: Something", "8/14/22 5:00 PM", "Jacob Jacobson"));
        customerAppointments.add(new CustomerAppointment("Help with Something 3", "8/14/22 5:00 PM", "Jacob Jacobson"));
        customerAppointments.add(new CustomerAppointment("Help with Something 4", "8/14/22 5:00 PM", "Jacob Jacobson"));
        customerAppointments.add(new CustomerAppointment("Help with Something 5", "8/14/22 5:00 PM", "Jacob Jacobson"));
         */

        customerAppointmentsRecyclerView.setAdapter(adapter);
        customerAppointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }
}