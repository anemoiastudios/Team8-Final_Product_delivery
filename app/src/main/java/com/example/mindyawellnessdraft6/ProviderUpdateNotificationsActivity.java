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

public class ProviderUpdateNotificationsActivity extends AppCompatActivity {

    private MaterialToolbar providerUpdateNotificationsToolbar;
    private MaterialButton providerUpdateNotificationsSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_update_notifications);

        createNotificationChannel();

        providerUpdateNotificationsToolbar = findViewById(R.id.providerUpdateNotificationsToolbar);
        providerUpdateNotificationsSaveButton = findViewById(R.id.providerUpdateNotificationsSaveButton);

        providerUpdateNotificationsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        providerUpdateNotificationsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProviderUpdateNotificationsActivity.this, CustomerReminder.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(ProviderUpdateNotificationsActivity.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                long timeAtButtonClick = System.currentTimeMillis();

                // Change this to change when we get notification
                long tenSecondsInMillis = 1000 * 3;

                alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMillis, pendingIntent);

                // finish();
            }
        });


    }

    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "ProviderReminderChannel";
            String description = "Channel for Provider Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("notifyProvider", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
}