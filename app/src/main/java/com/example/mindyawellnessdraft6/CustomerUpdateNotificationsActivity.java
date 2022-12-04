package com.example.mindyawellnessdraft6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class CustomerUpdateNotificationsActivity extends AppCompatActivity {

    private MaterialToolbar customerUpdateNotificationsToolbar;

    private MaterialButton customerUpdateNotificationsSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_update_notifications);

        createNotificationChannel();

        customerUpdateNotificationsToolbar = findViewById(R.id.customerUpdateNotificationsToolbar);
        customerUpdateNotificationsSaveButton = findViewById(R.id.customerUpdateNotificationsSaveButton);

        customerUpdateNotificationsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        customerUpdateNotificationsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CustomerUpdateNotificationsActivity.this, CustomerReminder.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(CustomerUpdateNotificationsActivity.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                long timeAtButtonClick = System.currentTimeMillis();

                // Change this to change when we get notification
                long tenSecondsInMillis = 1000 * 10;

                alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMillis, pendingIntent);


                // finish();
            }
        });

    }

    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "CustomerReminderChannel";
            String description = "Channel for Customer Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("notifyClient", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
}