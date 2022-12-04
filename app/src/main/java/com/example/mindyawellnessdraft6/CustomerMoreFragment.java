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
import android.widget.Switch;

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
                String entry = String.valueOf(adapterView.getItemAtPosition(i));

                switch(entry){
                    case "Local Shelters" :
                        Intent intent = new Intent(getActivity(), MoreLocalSheltersActivity.class);
                        startActivity(intent);
                        break;
                    case "Anxiety" :
                        Intent intent2 = new Intent(getActivity(),MoreAnxiety.class);
                        startActivity(intent2);
                        break;
                    case "Grants" :
                        Intent intent3 = new Intent(getActivity(),MoreGrantsActivity.class);
                        startActivity(intent3);
                        break;
                    case "Food Shelters" :
                        Intent intent4 = new Intent(getActivity(),MoreFoodSheltersActivity.class);
                        startActivity(intent4);
                        break;
                    case "Brain Injury" :
                        Intent intent5 = new Intent(getActivity(),MoreBrainInjuryActivity.class);
                        startActivity(intent5);
                        break;
                    case "Blood Pressure Recovery" :
                        Intent intent6 = new Intent(getActivity(),MoreBloodPressureRecoveryActivity.class);
                        startActivity(intent6);
                        break;
                    case "Car Accident Trauma" :
                        Intent intent7 = new Intent(getActivity(),MoreCarAccidentTraumaActivity.class);
                        startActivity(intent7);
                        break;
                    case "Mental Wellness Planning" :
                        Intent intent8 = new Intent(getActivity(),MoreMentalWellnessPlanningAcitivity.class);
                        startActivity(intent8);
                        break;
                    case "Schooling" :
                        Intent intent9 = new Intent(getActivity(),MoreSchoolingActivity.class);
                        startActivity(intent9);
                        break;
                    case "Rent" :
                        Intent intent10 = new Intent(getActivity(),MoreRentActivity.class);
                        startActivity(intent10);
                        break;
                    case "Insurance" :
                        Intent intent11 = new Intent(getActivity(),MoreInsuranceActivity.class);
                        startActivity(intent11);
                        break;
                    case "Bipolar" :
                        break;

                }
                /*Intent intent = new Intent(getActivity(), CustomerMoreEntriesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
            }
        });






        return view;
    }
}