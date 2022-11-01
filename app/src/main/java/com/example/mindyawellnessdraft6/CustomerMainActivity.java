package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CustomerMainActivity extends AppCompatActivity {

    private BottomNavigationView customerBottomNavigationView;

    private CustomerHomeFragment customerHomeFragment;
    private CustomerProvidersFragment customerProvidersFragment;
    private CustomerAppointmentsFragment customerAppointmentsFragment;
    private CustomerMoreFragment customerMoreFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        customerBottomNavigationView = findViewById(R.id.customerBottomNavigationView);

        customerHomeFragment = new CustomerHomeFragment();
        customerProvidersFragment = new CustomerProvidersFragment();
        customerAppointmentsFragment = new CustomerAppointmentsFragment();
        customerMoreFragment = new CustomerMoreFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.customerMainActivityContainer, customerHomeFragment).commit();

        customerBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.customerHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.customerMainActivityContainer, customerHomeFragment).commit();
                        return true;
                    case R.id.customerProviders:
                        getSupportFragmentManager().beginTransaction().replace(R.id.customerMainActivityContainer, customerProvidersFragment).commit();
                        return true;
                    case R.id.customerAppointments:
                        getSupportFragmentManager().beginTransaction().replace(R.id.customerMainActivityContainer, customerAppointmentsFragment).commit();
                        return true;
                    case R.id.customerMore:
                        getSupportFragmentManager().beginTransaction().replace(R.id.customerMainActivityContainer, customerMoreFragment).commit();
                        return true;

                }


                return false;
            }
        });









    }

    @Override
    public void onBackPressed() {
        return;
    }
}