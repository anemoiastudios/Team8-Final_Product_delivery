package com.example.mindyawellnessdraft6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CustomerAppointmentsFragment extends Fragment {

    private RecyclerView customerAppointmentsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_appointments, container, false);

        customerAppointmentsRecyclerView = view.findViewById(R.id.customerAppointmentsRecyclerView);

        ArrayList<CustomerAppointment> customerAppointments = new ArrayList<>();

        customerAppointments.add(new CustomerAppointment("Help with Something", "10/14/22 9:30 PM", "Doug Johnson"));
        customerAppointments.add(new CustomerAppointment("GROUP DISCUSSION: Alcoholics Anonymous", "10/15/22 10:45 AM", "Johnny Cash"));
        customerAppointments.add(new CustomerAppointment("Help with Something 2", "10/01/22 9:30 PM", "Selena Gomez"));
        customerAppointments.add(new CustomerAppointment("GROUP DISCUSSION: Trauma", "9/5/22 11:30 PM", "John Carmack"));
        customerAppointments.add(new CustomerAppointment("GROUP DISCUSSION: Something", "8/14/22 5:00 PM", "Jacob Jacobson"));
        customerAppointments.add(new CustomerAppointment("Help with Something 3", "8/14/22 5:00 PM", "Jacob Jacobson"));
        customerAppointments.add(new CustomerAppointment("Help with Something 4", "8/14/22 5:00 PM", "Jacob Jacobson"));
        customerAppointments.add(new CustomerAppointment("Help with Something 5", "8/14/22 5:00 PM", "Jacob Jacobson"));

        CustomerAppointmentsRecyclerViewAdapter adapter = new CustomerAppointmentsRecyclerViewAdapter(getActivity());
        adapter.setCustomerAppointments(customerAppointments);

        customerAppointmentsRecyclerView.setAdapter(adapter);
        customerAppointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }
}