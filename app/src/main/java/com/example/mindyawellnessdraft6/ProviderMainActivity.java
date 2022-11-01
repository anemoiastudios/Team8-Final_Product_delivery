package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class ProviderMainActivity extends AppCompatActivity {

    // private Button providerLogoutButton;
    private BottomNavigationView providerBottomNavigationView;

    private ProviderHomeFragment providerHomeFragment;
    private ProviderCustomersFragment providerCustomersFragment;
    private ProviderAppointmentsFragment providerAppointmentsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_main);

        providerBottomNavigationView = findViewById(R.id.providerBottomNavigationView);

        providerHomeFragment = new ProviderHomeFragment();
        providerCustomersFragment = new ProviderCustomersFragment();
        providerAppointmentsFragment = new ProviderAppointmentsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.providerMainActivityContainer, providerHomeFragment).commit();

        providerBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.providerHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.providerMainActivityContainer, providerHomeFragment).commit();
                        return true;
                    case R.id.providerCustomers:
                        getSupportFragmentManager().beginTransaction().replace(R.id.providerMainActivityContainer, providerCustomersFragment).commit();
                        return true;
                    case R.id.providerAppointments:
                        getSupportFragmentManager().beginTransaction().replace(R.id.providerMainActivityContainer, providerAppointmentsFragment).commit();
                        return true;
                }
                return false;
            }
        });

        /*
        providerLogoutButton = findViewById(R.id.providerLogoutButton);

        providerLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(ProviderMainActivity.this, WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish(); // Remove this and see what happens
            }
        });
         */
    }

    @Override
    public void onBackPressed() {
        return;
    }
}