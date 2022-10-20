package com.example.myw_app;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Appointment_page_activity extends AppCompatActivity {
    private Button upcomingButton, pastButton, otherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_recycler_view);

        RecyclerView appointmentRecyclerView = findViewById(R.id.appointmentRecyclerView);
        ArrayList<Appointment> appts = new ArrayList<>();

        appts.add(new Appointment("Monday, November 29, 2022", "12:30 AM PST", "Brian Stuart", "New York City"));
        appts.add(new Appointment("Monday, January 5, 2022", "2:30 pM PST", "Brian Stewie", "Michigan Cali"));


        AppointmentAdapter adapter = new AppointmentAdapter(appts);

        appointmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentRecyclerView.setAdapter(adapter);



        pastButton = findViewById(R.id.pastButton);
        otherButton = findViewById(R.id.otherButton);
        upcomingButton = findViewById(R.id.upcomingButton);

//        pastButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                pastButton.setBackgroundColor(Color.parseColor("#027989"));
//                pastButton.setTextColor(Color.WHITE);
//
//                upcomingButton.setTextColor(Color.parseColor("#027989"));
//                upcomingButton.setBackgroundColor(Color.parseColor("#DFDFDF"));
//
//                otherButton.setTextColor(Color.parseColor("#027989"));
//                otherButton.setBackgroundColor(Color.parseColor("#DFDFDF"));
//
//            }
//        });
//
//        otherButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                otherButton.setBackgroundColor(Color.parseColor("#027989"));
//                otherButton.setTextColor(Color.WHITE);
//
//                upcomingButton.setTextColor(Color.parseColor("#027989"));
//                upcomingButton.setBackgroundColor(Color.parseColor("#DFDFDF"));
//
//                pastButton.setTextColor(Color.parseColor("#027989"));
//                pastButton.setBackgroundColor(Color.parseColor("#DFDFDF"));
//            }
//        });
//
//        upcomingButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                upcomingButton.setBackgroundColor(Color.parseColor("#027989"));
//                upcomingButton.setTextColor(Color.WHITE);
//
//                pastButton.setTextColor(Color.parseColor("#027989"));
//                pastButton.setBackgroundColor(Color.parseColor("#DFDFDF"));
//
//                otherButton.setTextColor(Color.parseColor("#027989"));
//                otherButton.setBackgroundColor(Color.parseColor("#DFDFDF"));
//            }
//        });
    }
}
