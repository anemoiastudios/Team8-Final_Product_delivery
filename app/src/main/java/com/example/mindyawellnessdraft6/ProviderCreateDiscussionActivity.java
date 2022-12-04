package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class ProviderCreateDiscussionActivity extends AppCompatActivity {

    private MaterialToolbar providerCreateDiscussionToolbar;

    private EditText providerCreateDiscussionTitleEditText;
    private EditText providerCreateDiscussionDateEditText;
    private EditText providerCreateDiscussionTimeEditText;
    private EditText discussionDescription;

    private MaterialButton providerCreateDiscussionButton;

    private FirebaseAuth auth;
    private DatabaseReference discussionDatabaseReference;

    private String date = "";
    private String time = "";
    private int year;
    private int month;
    private int day;

    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_create_discussion);

        providerCreateDiscussionToolbar = findViewById(R.id.providerCreateDiscussionToolbar);

        providerCreateDiscussionToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        providerCreateDiscussionTitleEditText = findViewById(R.id.providerCreateDiscussionTitleEditText);
        providerCreateDiscussionDateEditText = findViewById(R.id.providerCreateDiscussionDateEditText);
        providerCreateDiscussionDateEditText.setFocusable(false);
        providerCreateDiscussionTimeEditText = findViewById(R.id.providerCreateDiscussionTimeEditText);
        providerCreateDiscussionTimeEditText.setFocusable(false);
        discussionDescription = findViewById(R.id.discussionDescription);

        providerCreateDiscussionButton = findViewById(R.id.providerCreateDiscussionButton);

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();

        providerCreateDiscussionDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();

            }
        });

        providerCreateDiscussionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String discussionTitle = providerCreateDiscussionTitleEditText.getText().toString();
                String discussionDate = date + " " + time;
                String discussionDes = discussionDescription.getText().toString();

                if(discussionTitle.length() != 0 && discussionDate.length() != 0){
                    // Create the discussion
                    String discussionId = System.currentTimeMillis() + "";
                    HashMap<String, Object> discussionInfo = new HashMap<>();
                    Discussion discussion = new Discussion(discussionTitle, discussionDate, discussionDes, userUid, discussionId);

                    discussionInfo.put(discussionId, discussion);

                    discussionDatabaseReference = FirebaseDatabase.getInstance().getReference().child("discussions");
                    discussionDatabaseReference.updateChildren(discussionInfo);

                    finish();

                }

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
                        providerCreateDiscussionDateEditText.setText(date);

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
                        providerCreateDiscussionTimeEditText.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
        timePickerDialog.show();
    }
}