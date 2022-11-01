package com.example.mindyawellnessdraft6;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ProviderHomeFragment extends Fragment {
    private MaterialToolbar providerHomeFragmentToolbar;

    private RecyclerView providerHomeFragmentDiscussionsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provider_home, container, false);

        providerHomeFragmentToolbar = view.findViewById(R.id.providerHomeFragmentToolbar);

        providerHomeFragmentToolbar.setOnMenuItemClickListener(new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;

                switch(item.getItemId()){
                    case R.id.providerSignOutButton:
                        FirebaseAuth.getInstance().signOut();

                        intent = new Intent(getActivity(), WelcomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();
                        return true;
                    case R.id.providerSettingsButton:
                        intent = new Intent(getActivity(), ProviderSettingsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);



                }
                return false;
            }
        });

        providerHomeFragmentDiscussionsRecyclerView = view.findViewById(R.id.providerHomeFragmentDiscussionsRecyclerView);

        ArrayList<Discussion> discussions = new ArrayList<>();

        discussions.add(new Discussion("Alcoholics Anonymous", "10/4/22"));
        discussions.add(new Discussion("Help with Mental", "10/2/22"));
        discussions.add(new Discussion("Help with Sadness :(", "10/10/22"));

        CustomerProviderProfileDiscussionListRecyclerViewAdapter adapter = new CustomerProviderProfileDiscussionListRecyclerViewAdapter(getActivity());
        adapter.setDiscussions(discussions);

        providerHomeFragmentDiscussionsRecyclerView.setAdapter(adapter);
        providerHomeFragmentDiscussionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));








        return view;
    }
}