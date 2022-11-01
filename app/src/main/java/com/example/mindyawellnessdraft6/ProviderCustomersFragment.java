package com.example.mindyawellnessdraft6;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProviderCustomersFragment extends Fragment {

    private ListView providerCustomerListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_customers, container, false);

        providerCustomerListView = view.findViewById(R.id.providerCustomerListView);


        ArrayList<String> customers = new ArrayList<>();

        customers.add("Customer 1");
        customers.add("Customer 2");
        customers.add("Customer 3");
        customers.add("Customer 4");
        customers.add("Customer 5");
        customers.add("Customer 6");
        customers.add("Customer 7");

        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_activated_1, customers);
        providerCustomerListView.setAdapter(adapter);

        providerCustomerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ProviderCustomerProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });





        // Inflate the layout for this fragment
        return view;
    }
}