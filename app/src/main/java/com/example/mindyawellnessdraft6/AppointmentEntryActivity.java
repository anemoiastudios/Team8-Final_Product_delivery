package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class AppointmentEntryActivity extends AppCompatActivity {

    private MaterialToolbar customerAppointmentEntryToolbar;

    private TextView customerAppointmentTitle;
    private TextView customerAppointmentDate;
    private TextView customerAppointmentProvider;
    private TextView appointmentClient;
    private TextView customerAppointmentDescription;

    private DatabaseReference appointmentsDatabaseReference;
    private DatabaseReference usersDatabaseReference;

    private Button goToCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_entry);

        customerAppointmentEntryToolbar = findViewById(R.id.customerAppointmentEntryToolbar);

        customerAppointmentTitle = findViewById(R.id.customerAppointmentTitle);
        customerAppointmentDate = findViewById(R.id.customerAppointmentDate);
        customerAppointmentProvider = findViewById(R.id.customerAppointmentProvider);
        appointmentClient = findViewById(R.id.appointmentClient);
        customerAppointmentDescription = findViewById(R.id.customerAppointmentDescription);

        goToCall = findViewById(R.id.goToCall);


        Bundle bundle = getIntent().getExtras();
        String appointmentId = bundle.getString("appointmentId");

        appointmentsDatabaseReference = FirebaseDatabase.getInstance().getReference().child("appointments").child(appointmentId);

        appointmentsDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                customerAppointmentTitle.setText(snapshot.child("appointmentTitle").getValue().toString());
                customerAppointmentDate.setText(snapshot.child("appointmentDate").getValue().toString() + " at " + snapshot.child("appointmentTime").getValue().toString());

                String clientUid = snapshot.child("customerUid").getValue().toString();
                String providerUid = snapshot.child("providerUid").getValue().toString();

                usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");

                usersDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Get client name
                        String firstName = snapshot.child(clientUid).child("privateInfo").child("firstName").getValue().toString();
                        String lastName = snapshot.child(clientUid).child("privateInfo").child("lastName").getValue().toString();
                        String clientFullName = firstName + " " + lastName;

                        // Get provider name
                        String providerFullName = snapshot.child(providerUid).child("privateInfo").child("fullName").getValue().toString();

                        // Set provider and client name on activity
                        customerAppointmentProvider.setText(providerFullName);
                        appointmentClient.setText(clientFullName);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                customerAppointmentDescription.setText(snapshot.child("appointmentDescription").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        customerAppointmentEntryToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String providerUID = bundle.getString("uid");

        goToCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {
                    JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(new URL("https://meet.jit.si/"))
                            .setRoom("Brian Stuart Capstone 33124233211")
                            .setAudioOnly(true)
                            .setSubject("providerUID")
                            .build();

                    JitsiMeetActivity.launch(AppointmentEntryActivity.this, options);

                } catch (MalformedURLException e) {
                    e.printStackTrace();

                }

            }
        });
    }
}