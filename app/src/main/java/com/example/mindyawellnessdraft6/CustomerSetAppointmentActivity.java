package com.example.mindyawellnessdraft6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class CustomerSetAppointmentActivity extends AppCompatActivity {

    private MaterialToolbar customerSetAppointmentToolbar;
    private MaterialButton customerSetAppointmentButton;

    private EditText customerAppointmentTitleEditText;
    private EditText customerAppointmentDateEditText;
    private EditText customerAppointmentTimeEditText;
    private EditText customerAppointmentDescriptionEditText;

    private FirebaseAuth auth;
    private DatabaseReference appointmentsDatabase;
    private DatabaseReference customerDatabase;
    private DatabaseReference providerDatabase;

    private String date = "";
    private String time = "";
    private int year;
    private int month;
    private int day;

    private int hour;
    private int minute;

    private String appointmentTitle;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentDescription;

    private String customerUid;
    private String customerFirstName;
    private String customerLastName;
    private String customerName;

    private String providerUid;
    private String appointmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_set_appointment);

        customerSetAppointmentToolbar = findViewById(R.id.customerSetAppointmentToolbar);
        customerSetAppointmentButton = findViewById(R.id.customerSetAppointmentButton);

        customerAppointmentTitleEditText = findViewById(R.id.customerAppointmentTitleEditText);

        customerAppointmentDateEditText = findViewById(R.id.customerAppointmentDateEditText);
        customerAppointmentDateEditText.setFocusable(false);

        customerAppointmentTimeEditText = findViewById(R.id.customerAppointmentTimeEditText);
        customerAppointmentTimeEditText.setFocusable(false);

        customerAppointmentDescriptionEditText = findViewById(R.id.customerAppointmentDescriptionEditText);

        customerSetAppointmentToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerAppointmentDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });

        customerSetAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the appointment information
                appointmentTitle = customerAppointmentTitleEditText.getText().toString();
                appointmentDate = date;
                appointmentTime = time;
                appointmentDescription = customerAppointmentDescriptionEditText.getText().toString();

                if(appointmentTitle.length() != 0 && appointmentDate.length() != 0 && appointmentTime.length() != 0 && appointmentDescription.length() != 0){
                    /*
                    Log.d("appointmentTitle", appointmentTitle);
                    Log.d("appointmentDate", appointmentDate);
                    Log.d("appointmentTime", appointmentTime);
                    Log.d("appointmentDescription", appointmentDescription);
                     */

                    // Get the customer information
                    auth = FirebaseAuth.getInstance();
                    customerUid = auth.getCurrentUser().getUid();

                    customerDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(customerUid).child("privateInfo");
                    customerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            customerFirstName = snapshot.child("firstName").getValue().toString();
                            customerLastName = snapshot.child("lastName").getValue().toString();

                            customerName = customerFirstName + " " + customerLastName;

                            // Get the provider information
                            Bundle bundle = getIntent().getExtras();
                            providerUid = bundle.getString("providerUid");

                            providerDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(providerUid).child("privateInfo");
                            providerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    appointmentProvider = snapshot.child("fullName").getValue().toString();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            // Create the appointment
                            String appointmentId = System.currentTimeMillis() + "";
                            HashMap<String, Object> appointmentInfo = new HashMap<>();
                            Appointment appointment = new Appointment(appointmentTitle, appointmentDate, appointmentTime, appointmentDescription, customerUid, providerUid, appointmentId);

                            appointmentInfo.put(appointmentId + "", appointment);

                            appointmentsDatabase = FirebaseDatabase.getInstance().getReference().child("appointments");
                            appointmentsDatabase.updateChildren(appointmentInfo);


                            Toast.makeText(CustomerSetAppointmentActivity.this, "You set up an appointment", Toast.LENGTH_SHORT).show();

                            finish();


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });







                } else {
                    Toast.makeText(CustomerSetAppointmentActivity.this, "Fill out everything", Toast.LENGTH_SHORT).show();
                }


                // finish();
            }
        });
    }

    private void datePicker(){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                        customerAppointmentDateEditText.setText(date);

                        timePicker();
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private void timePicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {

                        hour = hourOfDay;
                        minute = minuteOfDay;

                        time = hourOfDay + ":" + minute;
                        customerAppointmentTimeEditText.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
        timePickerDialog.show();
    }


}