package com.example.mindyawellnessdraft6;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class ProviderAppointmentsFragment extends Fragment {

    private MaterialToolbar providerAppointmentsToolbar;

    private RecyclerView providerRequestsRecyclerView;
    private RecyclerView providerAppointmentsRecyclerView;

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

                }

                return false;
            }
        });

        providerRequestsRecyclerView = view.findViewById(R.id.providerRequestsRecyclerView);

        ArrayList<CustomerRequest> customerRequests = new ArrayList<>();

        customerRequests.add(new CustomerRequest("Customer Name 1", "10/14/22 5:40 AM"));
        customerRequests.add(new CustomerRequest("Customer Name 2", "10/10/22 3:30 PM"));
        customerRequests.add(new CustomerRequest("Customer Name 3", "9/19/22 5:50 PM"));
        customerRequests.add(new CustomerRequest("Customer Name 4", "9/11/22 6:10 AM"));

        ProviderRequestRecyclerViewAdapter requestAdapter = new ProviderRequestRecyclerViewAdapter(getActivity());
        requestAdapter.setCustomerRequests(customerRequests);

        providerRequestsRecyclerView.setAdapter(requestAdapter);
        providerRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        providerAppointmentsRecyclerView = view.findViewById(R.id.providerAppointmentsRecyclerView);

        ArrayList<ProviderAppointment> providerAppointments = new ArrayList<>();

        providerAppointments.add(new ProviderAppointment("Appointment Title 1", "Date 1", "Customer Name 1"));
        providerAppointments.add(new ProviderAppointment("Appointment Title 2", "Date 2", "Customer Name 2"));
        providerAppointments.add(new ProviderAppointment("Appointment Title 3", "Date 3", "Customer Name 3"));
        providerAppointments.add(new ProviderAppointment("Appointment Title 4", "Date 4", "Customer Name 4"));
        providerAppointments.add(new ProviderAppointment("Appointment Title 5", "Date 5", "Customer Name 5"));

        ProviderAppointmentsRecyclerViewAdapter appointmentsAdapter = new ProviderAppointmentsRecyclerViewAdapter(getActivity());
        appointmentsAdapter.setProviderAppointments(providerAppointments);

        providerAppointmentsRecyclerView.setAdapter(appointmentsAdapter);
        providerAppointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        // Inflate the layout for this fragment
        return view;
    }
}