package com.example.mindyawellnessdraft6;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CustomerProvidersFragment extends Fragment {
    private RecyclerView customerProviderBookmarkedRecyclerView;
    private RecyclerView customerProviderAllRecyclerView;

    private MaterialButton instantChattingButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_providers, container, false);

        customerProviderBookmarkedRecyclerView = view.findViewById(R.id.customerProviderBookmarkedRecyclerView);

        // Hardcoded for now
        ArrayList<Provider> bookmarkedProviders = new ArrayList<>();
        bookmarkedProviders.add(new Provider("Carl Chandlers", "LCSW"));
        bookmarkedProviders.add(new Provider("Doug Carlson", "MFT"));
        bookmarkedProviders.add(new Provider("Carl Chandlers", "LCSW"));
        bookmarkedProviders.add(new Provider("Horatio Horatio", "LPC"));
        bookmarkedProviders.add(new Provider("Barack Obama", "LCSW"));

        ProvidersCardListRecyclerViewAdapter bookmarkedAdapter = new ProvidersCardListRecyclerViewAdapter(getActivity());
        bookmarkedAdapter.setProviders(bookmarkedProviders);

        customerProviderBookmarkedRecyclerView.setAdapter(bookmarkedAdapter);
        customerProviderBookmarkedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        customerProviderAllRecyclerView = view.findViewById(R.id.customerProviderAllRecyclerView);

        // Hardcoded for now
        ArrayList<Provider> allProviders = new ArrayList<>();
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

        ProvidersCardListRecyclerViewAdapter allAdapter = new ProvidersCardListRecyclerViewAdapter(getActivity());
        allAdapter.setProviders(allProviders);

        customerProviderAllRecyclerView.setAdapter(allAdapter);
        customerProviderAllRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        instantChattingButton = view.findViewById(R.id.instantChattingButton);

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
}