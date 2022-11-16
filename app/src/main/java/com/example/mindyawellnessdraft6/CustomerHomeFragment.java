package com.example.mindyawellnessdraft6;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerHomeFragment extends Fragment {

    // private Button customerProfileLogoutButton;
    private MaterialToolbar customerHomeFragmentToolbar;
    private TextView customerHomeMood;
    private TextView customerHomeBio;
    private TextView customerHomeLookingFor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_home, container, false);
        // setHasOptionsMenu(true);

        customerHomeFragmentToolbar = view.findViewById(R.id.customerHomeFragmentToolbar);

        customerHomeMood = view.findViewById(R.id.customerHomeMood);
        customerHomeBio = view.findViewById(R.id.customerHomeBio);
        customerHomeLookingFor = view.findViewById(R.id.customerHomeLookingFor);

        customerHomeMood.setText(CustomerUpdateProfileActivity.mood);
        customerHomeBio.setText(CustomerUpdateProfileActivity.bio);
        customerHomeLookingFor.setText(CustomerUpdateProfileActivity.lookingFor);

        customerHomeFragmentToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;

                switch(item.getItemId()){
                    case R.id.customerSignOutButton:
                        FirebaseAuth.getInstance().signOut();

                        intent = new Intent(getActivity(), WelcomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();
                        return true;

                    case R.id.customerSettingsButton:
                        intent = new Intent(getActivity(), CustomerSettingsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        /*
        customerProfileLogoutButton = view.findViewById(R.id.customerProfileLogoutButton);

        customerProfileLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();

            }
        });

         */

        return view;
    }
}