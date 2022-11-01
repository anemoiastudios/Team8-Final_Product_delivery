package com.example.mindyawellnessdraft6;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class CustomerMoreFragment extends Fragment {


    private ListView customerMoreListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_more, container, false);

        customerMoreListView = view.findViewById(R.id.customerMoreListView);

        ArrayList<String> customerMoreEntries = new ArrayList<>();

        customerMoreEntries.add("Local Shelters");
        customerMoreEntries.add("Anxiety");
        customerMoreEntries.add("Grants");
        customerMoreEntries.add("Food Shelters");
        customerMoreEntries.add("Brain Injury");
        customerMoreEntries.add("Blood Pressure Recovery");
        customerMoreEntries.add("Car Accident Trauma");
        customerMoreEntries.add("Mental Wellness Planning");
        customerMoreEntries.add("Schooling");
        customerMoreEntries.add("Rent");
        customerMoreEntries.add("Insurance");
        customerMoreEntries.add("Bipolar");



        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_selectable_list_item, customerMoreEntries);
        customerMoreListView.setAdapter(adapter);

        customerMoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), CustomerMoreEntriesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });






        return view;
    }
}