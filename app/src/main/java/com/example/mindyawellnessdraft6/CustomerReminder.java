package com.example.mindyawellnessdraft6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class CustomerReminder extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyClient")
                .setSmallIcon(R.drawable.ic_baseline_calendar_today_24)
                .setContentTitle("Mind Ya Wellness Upcoming Appointment")
                .setContentText("You have an upcoming appointment this week!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        /*
        NotificationCompat.Builder builder1 = new NotificationCompat.Builder(context, "notifyProvider")
                .setSmallIcon(R.drawable.ic_baseline_calendar_today_24)
                .setContentTitle("Mind Ya Wellness Meeting Soon!")
                .setContentText("A client will be meeting with you this week!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

         */

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // 200 is CustomerReminder
        notificationManager.notify(200, builder.build());
        // notificationManager.notify(201, builder1.build());
    }
}
